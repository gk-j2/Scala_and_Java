package fintech.lecture10

import doobie.syntax.connectionio._
import doobie.util.transactor.Transactor
import monix.eval.Task

import fintech.lecture10.database.CrawlerJob

trait AppService {
  def init: Task[Unit]

  def crawl(root: String, url: String): Task[CrawlerJob]

  def getJob(id: Long): Task[Option[CrawlerJob]]
}

class AppServiceImpl(crawler: Crawler, database: Database, transcator: Transactor[Task]) extends AppService {

  override def init: Task[Unit] =
    database.createTables.transact(transcator)

  override def crawl(root: String, url: String): Task[CrawlerJob] =
    for {
      result <- crawler.start(root, url)
      job = CrawlerJob(0L, root, url, result.pages)
      updatedJob <- database.saveCrawlerJob(job).transact(transcator)
    } yield updatedJob

  override def getJob(id: Long): Task[Option[CrawlerJob]] =
    database.loadResults(id).transact(transcator)
}
