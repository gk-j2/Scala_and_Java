package fintech.lecture10

import cats.effect.ExitCode
import cats.syntax.functor._
import io.circe.generic.semiauto._
import monix.eval.Task
import monix.eval.TaskApp
import org.http4s._
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl
import org.http4s.implicits._
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.server.Router

import scala.concurrent.duration._
import scala.util.Try

object Slide04Example1 {
  import org.http4s.Method.GET
  
  val x = Http4sDsl[Task]
  import x._

  val routes = HttpRoutes.of[Task] {
    case GET -> Root / "hello" =>
      Ok("Hello anonymous!")
  }
}

object Slide04Example2 extends TaskApp {
  import Slide04Example1._

  val dsl = Http4sDsl[Task]

  val httpApp = Router(
    "/greet" -> routes
  ).orNotFound

  override def run(args: List[String]): Task[ExitCode] =
    BlazeServerBuilder[Task].bindHttp(8080, "localhost")
      .withHttpApp(httpApp)
      .resource
      .use { _ =>
        Task.never
      } as ExitCode.Success
}

object Slide04Example3 {
  import org.http4s.Method.GET
  
  val x = Http4sDsl[Task]
  import x._

  val routes = HttpRoutes.of[Task] {
    case GET -> Root / "hello" =>
      Ok("Hello anonymous!")
  
    case GET -> Root / "hello" / name =>
      Ok(s"Hello, $name")
  
    case GET -> Root / "delay" / LongExtractor(ms) =>
      Ok(ms.toString)
  }
}

object LongExtractor {
  def unapply(str: String): Option[Long] =
    Try(str.toLong).toOption
}

object Slide04Example4 extends App {
  import org.http4s.Method.GET
  
  val x = Http4sDsl[Task]
  import x._

  def delay(ms: Long): Task[String] =
    Task.pure(ms.toString).delayExecution(ms.millis)

  val routes = HttpRoutes.of[Task] {
    case GET -> Root / "delay" / LongExtractor(ms) =>
      Ok(delay(ms))
  }
}

object Slide04Example5 {
  import org.http4s.Method.GET
  
  val x = Http4sDsl[Task]
  import x._

  case class FormattedDelay(message: String, delay: Long)

  implicit val formattedDelayEncoder = deriveEncoder[FormattedDelay]

  implicit val formattedDelayJsonEncoder = jsonEncoderOf[Task, FormattedDelay]

  def delay(ms: Long): Task[String] =
    Task.pure(ms.toString).delayExecution(ms.millis)

  val routes = HttpRoutes.of[Task] {

    case GET -> Root / "formatted-delay" / LongExtractor(ms) =>
      Ok(delay(ms).map(_ => FormattedDelay("Hello world", ms)))
  }
}
