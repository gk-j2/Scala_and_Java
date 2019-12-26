package fintech.lecture05

import scala.language.higherKinds

object Slide10Example1 {

  trait List[+A] {
    def map[B](func: A => B): List[B]
  }

  case object Nil extends List[Nothing] {
    override def map[B](func: Nothing => B) = Nil
  }

  case class Cons[A](head: A, tail: List[A]) extends List[A] {
    override def map[B](func: A => B) = Cons(func(head), tail.map(func))
  }
}

object Slide10Example2 {
  import Slide10Example1._

  val ints = Cons(1, Cons(2, Cons(3, Cons(4, Nil))))
  val squares = ints.map(i => i * i)

  val names = Cons("alice", Cons("bob", Cons("charlie", Nil)))
  val namelengths = names.map(_.size)
}

object Slide10Example3 {
  trait Option[+A] {
    def map[B](func: A => B): Option[B]
  }

  case object None extends Option[Nothing] {
    override def map[B](func: Nothing => B) = None
  }

  case class Some[A](a: A) extends Option[A] {
    override def map[B](func: A => B) = Some(func(a))
  }
}

object Slide10Example4 {

  def addOne(list: List[Int]): List[Int] = list.map(_ + 1)

  def addOne(option: Option[Int]): Option[Int] = option.map(_ + 1)
}

object Slide10Example5 {

  val a: Int = 1
  val string: String = "str"
  val boolean: Boolean = true

  case class Person(name: String)
  val person = Person("Alice")
}

object Slide10Example6 {

  trait Functor[F[_]] {
    def map[A, B](fa: F[A], func: A => B): F[B]
  }
}

object Slide10Example7 {
  import Slide10Example1._
  import Slide10Example6._

  val listFunctor = new Functor[List] {
    override def map[A, B](fa: List[A], func: A => B): List[B] =
      fa.map(func)
  }

  val optionFunctor = new Functor[Option] {
    override def map[A, B](fa: Option[A], func: A => B): Option[B] =
      fa.map(func)
  }
}

object Slide10Example8 {
  import Slide10Example1._
  import Slide10Example6._
  import Slide10Example7._

  def addOne[F[_]](fa: F[Int], functor: Functor[F]) = functor.map[Int, Int](fa, _ + 1)

  val list: List[Int] = Cons(1, Cons(2, Cons(3, Nil)))
  addOne(list, listFunctor)

  val option: Option[Int] = Some(1)
  addOne(option, optionFunctor)
}
