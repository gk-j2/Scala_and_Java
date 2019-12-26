package fintech.lecture.examples

import scala.collection.{IterableLike, TraversableLike, mutable}

object Example11List {
  // construct
  val list1 = List(1,2,3,4,5)
  val list2 = 1 :: 2 :: 3 :: 4 :: 5 :: Nil
  val list3 = List.tabulate(10)(n => n * 2)

  //access
  val head = list1.head // not good
  val tail = list1.tail // also not good
  val headOpt = list1.headOption

  // with pattern matching
  list2 match {
    case tail :: head => s"$head and $tail"
    case Nil => "this is the end"
  }

  //update above and add element
  val list4 = 0 :: list2
}
