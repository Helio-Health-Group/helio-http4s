package com.helio

import cats.data.Kleisli
import cats.effect._
import com.helio.models.database.generated.Acronym
import io.circe.generic.auto._
import io.circe.syntax._
import models.ServerInfo
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.blaze._
import scalikejdbc.sqls

import scala.concurrent.ExecutionContext.Implicits.global

object HttpService extends IOApp {

  val infoService: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "ping" / msg =>
      Ok(ServerInfo(msg).asJson)
  }

  val apiService: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "acronyms" / name =>
      val a = Acronym.a
      val acronyms = Acronym.findAllBy(sqls.eq(a.acronym, name))
      Ok(acronyms.asJson)
  }

  val httpApp: Kleisli[IO, Request[IO], Response[IO]] = Router(
    "info" -> infoService,
    "api" -> apiService
  ).orNotFound

  def run(args: List[String]): IO[ExitCode] = {
    Database.connect() match {
      case Left(_) =>
        println("Unable to connect to database")
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
