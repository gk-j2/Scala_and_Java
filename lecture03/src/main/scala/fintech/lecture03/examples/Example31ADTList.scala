package fintech.lecture03.examples

import scala.annotation.tailrec

object Example31ADTList extends App {

  sealed trait Linked[+T]
  final case class Link[T](head: T, tail: Linked[T]) extends Linked[T]
  case object Empty extends Linked[Nothing]

  object Linked {
    def apply[T](elems: T*): Linked[T] =
      if (elems.isEmpty) Empty else Link(elems.head, apply(elems.tail: _*))
  }

  val ints = Linked(1, 2, 3, 4, 5)

  @tailrec
  def foreach[T](list: Linked[T])(f: T => Unit): Unit = list match {
    case Empty =>
    case Link(head, tail) =>
      f(head)
      foreach(tail)(f)
  }

  foreach(ints)(println)

}
