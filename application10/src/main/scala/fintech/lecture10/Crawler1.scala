package fintech.lecture10a

import io.chrisdavenport.log4cats.slf4j.Slf4jLogger
import monix.eval.Task
import org.http4s.client.Client

import scala.util.Try

final case class CrawlerResult(pages: List[(String, Int)])

class Crawler(client: Client[Task], maxDepth: Int, maxUrlsFromPage: Int, maxParallelReqs: Int) {

  private val logger = Slf4jLogger.getLoggerFromClass[Task](classOf[Crawler])

  def start(root: String, url: String): Task[CrawlerResult] = ???

  val pattern = "href=[\"\']([^\"\']+)[\"\']".r
  def extractUrls(root: String, html: String): List[String] =
    Try {
      (for {
        m <- pattern.findAllMatchIn(html) if m.group(1).endsWith("html")
      } yield {
        val url = m.group(1)
        if (url.startsWith("http")) {
          url
        } else if (url.startsWith("/")) {
          root + url
        } else {
          root + "/" + url
        }
      }).toList
    } getOrElse Nil
}
