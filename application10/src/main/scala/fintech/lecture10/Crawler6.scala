package fintech.lecture10f

import cats.instances.int._
import cats.syntax.eq._
import io.chrisdavenport.log4cats.slf4j.Slf4jLogger
import monix.catnap.ConcurrentQueue
import monix.catnap.MVar
import monix.eval.Task
import org.http4s.client.Client

import scala.util.Try

final case class CrawlerResult(pages: List[(String, Int)])

sealed trait CrawlerMessage
final case class Start(
  root: String,
  url: String
) extends CrawlerMessage
final case class PageRequest(
  root: String,
  url: String,
  depth: Int
) extends CrawlerMessage
final case class PageResponse(
  request: PageRequest,
  newUrls: List[String],
  response: String
) extends CrawlerMessage

final case class CrawlerState(
  queue: ConcurrentQueue[Task, CrawlerMessage],
  resultLatch: MVar[Task, CrawlerResult],
  acc: List[(String, Int)],
  visited: Set[String],
  inQueue: Int
)

class Crawler(client: Client[Task], maxDepth: Int, maxUrlsFromPage: Int, maxParallelReqs: Int) {

  private val logger = Slf4jLogger.getLoggerFromClass[Task](classOf[Crawler])

  def start(root: String, url: String): Task[CrawlerResult] =
    for {
      mvar <- MVar.empty[Task, CrawlerResult]()
      queue <- ConcurrentQueue.bounded[Task, CrawlerMessage](1024)
      _ <- queue.offer(Start(root, url))
      _ <- handleQueue(CrawlerState(queue, mvar, Nil, Set.empty, 1)).start
      result <- mvar.read
    } yield result

  private def handleQueue(state: CrawlerState): Task[Unit] =
    state.queue.poll.flatMap { message =>
      val (effect, nextState) = handleMessage(state, message)
      if (nextState.inQueue === 0) {
        state.resultLatch.put(CrawlerResult(nextState.acc))
      } else {
        effect.start >> handleQueue(nextState)
      }
    }

  private def handleMessage(state: CrawlerState, message: CrawlerMessage): (Task[Unit], CrawlerState) =
    message match {
      case Start(root, url) =>
        (state.queue.offer(PageRequest(root, url, maxDepth)), state)
      case req @ PageRequest(root, url, _) =>
        val effect =
          for {
            html <- client.expect[String](url)
            _ <- logger.info(s"Sending request for $url")
            urls = extractUrls(root, html)
            _ <- state.queue.offer(PageResponse(req, urls, html))
          } yield ()
        (effect, state.copy(visited = state.visited + url))
    }

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
