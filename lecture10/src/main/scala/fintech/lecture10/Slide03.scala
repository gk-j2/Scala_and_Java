package fintech.lecture10

import monix.execution.Scheduler.Implicits.global
import monix.eval.Task

sealed trait Method
case object GET extends Method
case object POST extends Method

sealed trait StatusCode
case object OK extends StatusCode
case object NotFound extends StatusCode

case class Request(method: Method, url: String, headers: Map[String, String])
case class Response(status: StatusCode, content: String)


object Slide03Example1 extends App {

  type HttpApp = Request => Response

  val helloWorld: HttpApp = {
    case Request(POST, "/hello", _) =>
      Response(OK, "Hi")
    case _ =>
      Response(NotFound, "")
  }

  // test
  val req = Request(POST, "/hello", Map.empty)
  helloWorld(req).content == "Hi"
}

object Translator {
  def translate(text: String): Task[String] = ???
}

object Slide03Example2 {

  type HttpApp = Request => Response

  val helloWorld: HttpApp = {
    case Request(POST, url, _) if url.startsWith("/translate/") =>
      val resp: Task[String] = Translator.translate(url.drop("/translate".length))
      Response(OK, resp.runSyncUnsafe())
    case _ =>
      Response(NotFound, "")
  }
}

object Slide03Example3 {

  type HttpApp = Request => Task[Response]

  val helloWorld: HttpApp = {
    case Request(POST, url, _) if url.startsWith("/translate/") =>
      Translator.translate(url.drop("/translate".length))
        .map(Response(OK, _))
    case _ =>
      Task.pure(Response(NotFound, ""))
  }
}

object Slide03Example4 {

  type HttpApp = Request => Task[Response]

  val engHello: HttpApp = {
    case Request(POST, "/hello", _) =>
      Task.pure(Response(OK, "hi"))
  }

  val rusHello: HttpApp = {
    case Request(POST, "/привет", _) =>
      Task.pure(Response(OK, "дратути"))
  }

  def combine(x: HttpApp, y: HttpApp): HttpApp = (req: Request) => {
    try {
      x(req)
    } catch {
      case _: MatchError => y(req)
    }
  }

  val combinedApp: HttpApp = combine(engHello, rusHello)
}

object Slide03Example5 {

  type HttpRoute = PartialFunction[Request, Task[Response]]

  type HttpApp = Request => Task[Response]

  val engHello: HttpRoute = {
    case Request(POST, "/hello", _) =>
      Task.pure(Response(OK, "hi"))
  }

  val rusHello: HttpRoute = {
    case Request(POST, "/привет", _) =>
      Task.pure(Response(OK, "дратути"))
  }

  def combine(x: HttpRoute, y: HttpRoute): HttpRoute = x.orElse(y)

  val combinedApp: HttpApp = combine(engHello, rusHello)

  //test
  combinedApp(Request(POST, "salut", Map.empty))
}

object Slide03Example6 {
  type HttpRoute = Request => Option[Task[Response]]

  type HttpApp = Request => Task[Response]

  object HttpRoute {
    def of(pf: PartialFunction[Request, Task[Response]]): HttpRoute = pf.lift
  }

  val engHello: HttpRoute = HttpRoute.of({
    case Request(POST, "/hello", _) =>
      Task.pure(Response(OK, "hi"))
  })

  val rusHello: HttpRoute = HttpRoute.of({
    case Request(POST, "/привет", _) =>
      Task.pure(Response(OK, "дратути"))
  })

  def combine(x: HttpRoute, y: HttpRoute): HttpRoute =
    (req: Request) => x(req).orElse(y(req))

  //val combinedApp: HttpApp = combine(engHello, rusHello)

  def seal(routes: HttpRoute): HttpApp =
    routes.andThen(_.getOrElse(Task.pure(Response(NotFound, "not-found"))))

  val combinedApp: HttpApp = seal(combine(engHello, rusHello))

  //test
  combinedApp(Request(POST, "salut", Map.empty))
}

object Slide03Example7 {
  import Slide03Example6._

  def translate(app: HttpApp): HttpApp =
    app.andThen(task => for {
      resp <- task
      translation <- Translator.translate(resp.content)
    } yield resp.copy(content = translation))

  val combinedApp: HttpApp = translate(seal(combine(engHello, rusHello)))
}
