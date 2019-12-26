package fintech.lecture04.examples

object Ex22CurryAndPartApply extends App {
  val func12 = (x: Int, y: Int) =>  s"$x$x$x$y"
  val func2 = (_: String).toInt

  val func11 = func12(_: Int, 0) // fixed y

  val x: Int => Int => String = func12.curried
  val func1 = x(0): Int => String // fixed x

  println(func11(2))
  println(func1(2))
}
