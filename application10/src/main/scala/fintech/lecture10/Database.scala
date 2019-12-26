package fintech.lecture10

import cats.instances.list._
import doobie.ConnectionIO
import doobie.Update
import doobie.implicits._

import fintech.lecture10.database.CrawlerJob

trait Database {

  val createTables =
    for {
      _ <- sql"""create table REQUESTS(
        ID bigint auto_increment,
        ROOT varchar(128) not null,
        URL varchar(256) not null
      )""".update.run
      _ <- sql"""create table RESULTS(
        ID bigint,
        URL varchar(256) not null,
        SIZE int not null
      )""".update.run
    } yield ()

  def loadResults(id: Long): ConnectionIO[Option[CrawlerJob]] =
    for {
      mbJob <- sql"""select ROOT, URL from REQUESTS where ID = $id""".query[(String, String)].option
      pages <- sql"""select URL, SIZE from RESULTS where ID = $id""".query[(String, Int)].to[List]
    } yield mbJob.map { case (root, url) => CrawlerJob(id, root, url, pages) }

  def saveCrawlerJob(job: CrawlerJob): ConnectionIO[CrawlerJob] =
    for {
      newId <- sql"""insert into REQUESTS(ROOT, URL) values (${job.root}, ${job.url})"""
        .update
        .withUniqueGeneratedKeys[Long]("ID")
      _ <- Update[(Long, String, Int)]("insert into RESULTS(ID, URL, SIZE) values (?, ?, ?)")
        .updateMany(job.pages.map { case (url, size) => (newId, url, size) })
    } yield job.copy(id = newId)
}

object Database extends Database
