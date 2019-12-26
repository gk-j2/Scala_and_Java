package fintech.lecture.examples

object Example21Grouping extends App {
  val list = List(1, 2, 3, 4, 5, 5, 6, 7)

  // can be group by
  println(
    list.groupBy(x => x % 2)
  )

  // can be grouped to Iterator
  println(
    list.grouped(3).toList
  )

  // can be slided to Iterator
  println(
    list.sliding(3).toList
  )

  println(
    list.tails.toList
  )
}