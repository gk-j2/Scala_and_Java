package fintech.lecture10

import cats.effect.ExitCode
import cats.syntax.functor._
import monix.eval.Task
import monix.eval.TaskApp
import org.http4s.dsl.Http4sDsl
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.Router

import fintech.lecture10.endpoints.HelloWorldEndpoint

object HelloWorldMain extends TaskApp {

  val dsl = Http4sDsl[Task]

  val httpApp = Router(
    "/greet" -> new HelloWorldEndpoint(dsl).routes
  ).orNotFound

  override def run(args: List[String]): Task[ExitCode] =
    BlazeServerBuilder[Task].bindHttp(8080, "localhost")
      .withHttpApp(httpApp)
      .resource
      .use { _ =>
        Task.unit
      } as ExitCode.Success
}
