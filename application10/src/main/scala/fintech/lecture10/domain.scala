package fintech.lecture10.database

final case class CrawlerJob(
  id: Long,
  root: String,
  url: String,
  pages: List[(String, Int)]
)
