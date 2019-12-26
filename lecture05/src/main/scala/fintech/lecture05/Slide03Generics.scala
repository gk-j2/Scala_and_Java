package fintech.lecture05

object Slide03Example1 {

  // var xs: List = null

}

object Slide03Example2 {

  sealed trait List[+A]

  case class Cons[A](head: A, tail: List[A]) extends List[A]

  case class Nil[A]() extends List[A]



  val xs: List[Any] = Cons(1, Cons(2, Nil()))
}

object Slide03Example3 {

  case class Box[A](var value: A)

  val c1: Box[String] = Box("str")
  val c2: Box[Any] = c1
  c2.value = 1
  val string: String = c1.value
}
