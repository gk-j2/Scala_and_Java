package fintech.lecture.examples

import scala.annotation.tailrec

object Example23LazyList extends App {

  sealed trait LazyList[+A]

  case object Empty extends LazyList[Nothing]

  case class Cons[+A](h: () => A, t: () => LazyList[A]) extends LazyList[A]

  object LazyList {
    def cons[A](h: => A, t: => LazyList[A]): LazyList[A] = {
      lazy val head = h
      lazy val tail = t
      Cons(() => head, () => tail)
    }

    def empty[A]: LazyList[A] = Empty

    def apply[A](xs: A*): LazyList[A] =
      if (xs.isEmpty) empty else cons(xs.head, apply(xs.tail: _*))
  }


  // infinite is not the limit
  def infiniteOne: LazyList[Int] = LazyList.cons(1, infiniteOne)

  @tailrec
  def foreach[A](xs: LazyList[A])(f: A => Unit): Unit = {
    xs match {
      case Empty => ()
      case Cons(head, tail) =>
        f(head()); foreach(tail())(f)
    }
  }

  foreach(infiniteOne)(println)
}
