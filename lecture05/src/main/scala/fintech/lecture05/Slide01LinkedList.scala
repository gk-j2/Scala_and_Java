package fintech.lecture05

object Slide01Example1 {

  sealed trait IntList

  final case class Cons(head: Int, tail: IntList) extends IntList

  case object Nil extends IntList

}

object Slide01Example2 {
  import Slide01Example1._

  val d = Nil
  val c = Cons(3, d)
  val b = Cons(2, c)
  val a = Cons(1, b)

  // or shorter
  val xs = Cons(1, Cons(2, Cons(3, Nil)))
}

object Slide01Example3 {
  import Slide01Example1._

  def size(xs: IntList): Int =
    xs match {
      case Nil => 0
      case Cons(_, tail) => 1 + size(tail)
    }
}

object Slide01Example4 {

  sealed trait DoubleList

  final case class Cons(head: Double, tail: DoubleList) extends DoubleList

  case object DoubleNil extends DoubleList

}
