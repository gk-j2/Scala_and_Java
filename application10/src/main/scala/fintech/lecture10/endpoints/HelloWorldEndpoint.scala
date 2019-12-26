package fintech.lecture10.endpoints

import monix.eval.Task
import org.http4s.HttpRoutes
import org.http4s.dsl.Http4sDsl

class HelloWorldEndpoint(dsl: Http4sDsl[Task]) {
  import dsl._

  val routes = HttpRoutes.of[Task] {
    case GET -> Root / "hello" =>
      Ok("Hello anonymous!")
    case GET -> Root / "hello" / name =>
      Ok(s"Hello, $name")
  }
}
