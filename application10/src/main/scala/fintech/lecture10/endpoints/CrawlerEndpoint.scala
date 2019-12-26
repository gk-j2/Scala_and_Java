package fintech.lecture10.endpoints

import io.circe.Encoder
import io.circe.Decoder
import io.circe.generic.semiauto._
import io.circe.syntax._
import monix.eval.Task
import org.http4s.HttpRoutes
import org.http4s.circe._
import org.http4s.dsl.Http4sDsl

import scala.util.Try

import fintech.lecture10.AppService
import fintech.lecture10.database.CrawlerJob

class CrawlerEndpoint(dsl: Http4sDsl[Task], service: AppService) {
  import dsl._
  import CrawlerEndpoint._

  implicit val decoder = jsonOf[Task, CrawlerRequest]

  val routes = HttpRoutes.of[Task] {
    case req @ POST -> Root / "run" =>
      for {
        request <- req.as[CrawlerRequest]
        result <- service.crawl(request.root, request.url)
        response <- Ok(result.asJson)
      } yield response
    case GET -> Root / "history" / LongExtractor(id) =>
      for {
        job <- service.getJob(id)
        response <- Ok(job.asJson)
      } yield response
  }
}

object LongExtractor {
  def unapply(str: String): Option[Long] = Try(str.toLong).toOption
}

object CrawlerEndpoint {
  implicit val crawlerJobEncoder: Encoder[CrawlerJob] = deriveEncoder[CrawlerJob]

  implicit val crawlerRequestDecoder: Decoder[CrawlerRequest] = deriveDecoder[CrawlerRequest]
}
