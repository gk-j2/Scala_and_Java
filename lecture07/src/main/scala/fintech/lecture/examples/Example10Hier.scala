package fintech.lecture.examples

import scala.collection.{GenTraversableLike, IterableLike, TraversableLike, mutable}

object Example10Hier {
  val traversableLike: TraversableLike[Int, List[Int]] = List(1,2,3)
  val traversable: Traversable[Int] = List(1,2,3)

  val iterableLike: IterableLike[Int, List[Int]] = List(1,2,3)
  val iterable: Iterable[Int] = List(1,2,3)

  val indexedSeq: IndexedSeq[String] = Vector("one","two","three")

  val immutableList = List(1,2,3)
  val mutableList = mutable.ListBuffer(1,2,3)

  val immutableMap = Map(1 -> "one", 2 -> "two", 3 -> "three")
  val mutableMap = mutable.Map(1 -> "one", 2 -> "two", 3 -> "three")

  val newIter: Iterable[String] = immutableMap.map{kv => kv._1.toString}
  val newMap: Map[String,String] = immutableMap.map(kv => kv._1.toString -> kv._2)
}
