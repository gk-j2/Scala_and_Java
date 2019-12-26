package fintech.lecture.exercises

object Exercise03TopNGrams extends App {
  def topNGrams(n: Int, limit: Int,
                blackList: Set[String])(text: String): List[String] =
    text
      .sliding(n).filterNot(blackList).toList
      .groupBy(identity).map{case(word, xs) => word -> xs.size}.toList
      .sortWith{case (x1, x2) => x1._2 < x2._2}
      .take(limit)
      .map(_._1)

  val text = "asdasdasddddddddddd"
  topNGrams(2, 10, Set.empty)(text)
}
