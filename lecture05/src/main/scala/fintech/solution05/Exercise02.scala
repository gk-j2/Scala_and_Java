package fintech.solution05

import fintech.lecture05.Slide02Example4.Cons
import fintech.lecture05.Slide02Example4.Nil

sealed trait Maybe[T] {

  def foreach(f: T => Unit): Unit

  def count: Int
}

case class Just[T](t: T) extends Maybe[T] {

  override def foreach(f: T => Unit): Unit = f(t)

  override def count: Int = 1
}

case class Missing[T]() extends Maybe[T] {

  override def foreach(f: T => Unit): Unit = ()

  override def count: Int = 0
}

object Exercise02 {
  import fintech.lecture05.Slide02Example4._

  def oneLevel[T](xs: List[Maybe[T]]): Maybe[List[T]] = {
    def internal(maybeAcc: Maybe[List[T]], xs: List[Maybe[T]]): Maybe[List[T]] =
      maybeAcc match {
        case Just(acc) =>
          xs match {
            case Cons(Just(t), tail) =>
              internal(Just(Cons(t, acc)), tail)
            case Cons(Missing(), tail) =>
              Missing()
            case Nil() =>
              Just(acc)
          }
        case res @ Missing() =>
          res
      }

    def reverse(acc: List[T], xs: List[T]): List[T] =
      xs match {
        case Cons(head, tail) => 
          reverse(Cons(head, acc), tail)
        case Nil() =>
          acc
      }


    internal(Just(Nil()), xs) match {
      case Just(result) => Just(reverse(Nil(), result))
      case res @ Missing() => res
    }
  }

  def main(args: Array[String]): Unit = {
    val maybes: List[Maybe[Int]] =
      Cons(Just(1), Cons(Missing[Int], Cons(Just(3), Nil[Maybe[Int]]())))
      //Cons(Just(1), Cons(Just(2), Cons(Just(3), Nil[Maybe[Int]]())))

    println(oneLevel(maybes))
  }
}
