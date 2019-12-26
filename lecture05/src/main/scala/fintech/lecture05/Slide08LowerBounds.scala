package fintech.lecture05

object Slide08Example1 {

  sealed trait List[+A] {
    def prepend[B >: A](e: B): List[B]
  }

  case class Cons[A](head: A, tail: List[A]) extends List[A] {
    override def prepend[B >: A](e: B): List[B] = Cons(e, this)
  }

  case object Nil extends List[Nothing] {
    override def prepend[B](e: B): List[B] = Cons(e, Nil)
  }
}

object Slide08Example2 {
  import Slide08Example1._

  val ints: List[Int] = Cons(1, Cons(2, Nil))

  val strings: List[String] = Cons("a", Cons("b", Nil))
}
