package fintech.lecture10

import cats.effect.Blocker
import cats.effect.ExitCode
import cats.effect.Resource
import cats.syntax.functor._
import doobie.h2.H2Transactor
import monix.eval.Task
import monix.eval.TaskApp
import monix.execution.Scheduler
import org.http4s.client.blaze.BlazeClientBuilder
import org.http4s.server.blaze.BlazeServerBuilder
import org.http4s.dsl.Http4sDsl
import org.http4s.implicits._
import org.http4s.server.Router

import fintech.lecture10.endpoints.CrawlerEndpoint
import fintech.lecture10.endpoints.HelloWorldEndpoint

case class CrawlerRequest(root: String, url: String)

/*
 * curl -s -X GET "localhost:8080/greet/hello"
 *
 * curl -s -X GET "localhost:8080/greet/hello/World"
 *
 * curl -s -X POST "localhost:8080/crawler/run" -H "Content-Type: application/json" --data '{"root": "https://typelevel.org", "url":  "https://typelevel.org/cats-effect/datatypes/"}' | jq "."
*/

object Main extends TaskApp {

  val blockerResource = Resource.make {
    val scheduler = Scheduler.io("blocking-scheduler")
    Task.delay((scheduler, Blocker.liftExecutionContext(scheduler)))
  } { case (scheduler, _) => Task.delay(scheduler.shutdown) } map (_._2)

  val appServiceResource: Resource[Task, AppService] =
    for {
      client <- BlazeClientBuilder[Task](scheduler).resource
      blocker <- blockerResource
      transactor <- H2Transactor.newH2Transactor[Task](
        "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1",
        "username", "password",
        scheduler, blocker
      )
    } yield new AppServiceImpl(new Crawler(client, 3, 3, 1), Database, transactor)

  override def run(args: List[String]): Task[ExitCode] =
    appServiceResource.use { service =>
      val dsl = Http4sDsl[Task]
      val httpApp = Router(
        "/greet" -> new HelloWorldEndpoint(dsl).routes,
        "/crawler" -> new CrawlerEndpoint(dsl, service).routes
      ).orNotFound

      val server = BlazeServerBuilder[Task].bindHttp(8080, "localhost")
        .withHttpApp(httpApp)
        .resource
        .use { _ =>
          Task.never
        }

      service.init >> server
    } as ExitCode.Success
}
