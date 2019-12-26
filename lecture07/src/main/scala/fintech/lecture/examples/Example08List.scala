package fintech.lecture.examples

// meet dat List: HEAD AND TAIL
object Example08List extends App {
  val listValue: List[Int] = List(14, 88)

  // do that sometimes
  listValue match {
    case x :: xs => println(s"some head $x and tail $xs")
    case Nil => println("this is the end")
  }

  // AND DO THAT WAY !!!

  val transformValue = listValue.map(_ * 3)
  val transformValue2 = listValue.flatMap(x => List(x * 2, x * 3))
  val valueWithFallback = listValue.fold(2)((x, y) => x * y)
  listValue.foreach(println)
}
