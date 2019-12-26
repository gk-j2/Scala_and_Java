package fintech.lecture03.examples

object Example14ClassFixedTypeParameters extends App {

  type IntList = List[Int]

  val ints: IntList = List(1, 2, 3)

  type Indexed[A] = Map[Int, A]

  val indexedStrings: Indexed[String] = Map(1 -> "one", 42 -> "forty-two")

}
