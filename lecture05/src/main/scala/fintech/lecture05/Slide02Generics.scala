package fintech.lecture05

object Slide02Example1 extends App {

  final case class Box[A](value: A)

  val box1 = Box(2)
  println(box1.value)

  val box2 = Box("Hi")
  println(box2.value)

}

object Slide02Example2 extends App {

  def generic[A](input: A): A = input

  println(generic(1))

}

object Slide02Example3 {
  import Slide02Example2._

  val v1 = generic[Int](1)

  val v2 = generic[Any](1)
}

object Slide02Example4 extends App {

  sealed trait List[A]
  case class Cons[A](head: A, tail: List[A]) extends List[A]
  case class Nil[A]() extends List[A]

  def size[A](xs: List[A]): Int =
    xs match {
      case Nil() => 0
      case Cons(_, tail) => 1 + size(tail)
    }

  val stringsList = Cons("a", Cons("b", Cons("c", Nil())))
  println(size(stringsList))

  val intsList = Cons(1, Cons(2, Nil()))
  println(size(intsList))
}
