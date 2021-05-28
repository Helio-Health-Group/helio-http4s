package com.helio

import cats.data.Kleisli
import cats.effect._
import com.helio.models.ServerInfo
import com.helio.models.database.generated.Acronym
import io.circe.generic.auto._
import io.circe.syntax._
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.blaze._
import org.log4s.{Logger, getLogger}

import scala.concurrent.ExecutionContext.Implicits.global

object HttpService extends IOApp {
  private val log: Logger = getLogger

  implicit val serverInfoEnc: EntityDecoder[IO, ServerInfo] = jsonOf[IO, ServerInfo]

  val infoService: HttpRoutes[IO] = HttpRoutes.of[IO] {
    // e.g., http://localhost:8080/info/ping/hello
    case GET -> Root / "ping" / msg =>
      Ok(ServerInfo(msg).asJson)
    case req@POST -> Root / "pong" =>
      // POST http://localhost:8080/info/pong
      // {
      //    "msg": "Hello",
      //    "name": "World",
      //    "timestamp": "2021-04-07T13:48:03.1049861"
      //}
      // Will echo the payload
      val info = req.as[ServerInfo]
      info.flatMap { // We could also use the for comprehension here
        i =>
          Ok(i.asJson)
      }
  }

  val apiService: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "acronyms" / IntVar(id) =>
      val acronyms = Acronym.find(id)
      acronyms match {
        case Some(a) => Ok(a.asJson)
        case None => NotFound(s"Acronym $id not found")
      }
  }

  val httpApp: Kleisli[IO, Request[IO], Response[IO]] = Router(
    "info" -> infoService,
    "api" -> apiService
  ).orNotFound

  def run(args: List[String]): IO[ExitCode] = {
    Database.connect() match {
      case Left(_) =>
        log.error("Unable to connect to database")
        IO(ExitCode.Error)
      case Right(_) =>
        BlazeServerBuilder[IO](global)
          .bindHttp(8080, "localhost")
          .withHttpApp(httpApp)
          .serve
          .compile
          .drain
          .as(ExitCode.Success)
    }
  }
}
