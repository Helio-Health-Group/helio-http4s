import cats.data.Kleisli
import cats.effect._
import io.circe.generic.auto._
import io.circe.syntax._
import models.ServerInfo
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.server.Router
import org.http4s.server.blaze._

import scala.concurrent.ExecutionContext.Implicits.global

object HttpService extends IOApp {

  val infoService: HttpRoutes[IO] = HttpRoutes.of[IO] {
    case GET -> Root / "ping" / msg =>
      Ok(ServerInfo(msg).asJson)
  }

  val httpApp: Kleisli[IO, Request[IO], Response[IO]] = Router(
    "info" -> infoService,
  ).orNotFound

  def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO](global)
      .bindHttp(8080, "localhost")
      .withHttpApp(httpApp)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
}
