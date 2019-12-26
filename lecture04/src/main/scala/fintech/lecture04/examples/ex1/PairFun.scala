package org.urfu.scalafp.lection04.ex1

object PairFun extends App {

  type Pair[A, B] = ((A, B) => Either[A, B]) => Either[A, B]

  def pair[A, B](a: A, b: B): Pair[A, B] =  m => m(a, b)

  def lft[A, B](pair: Pair[A, B]): Either[A, B] =
    pair( (a, _) => Left(a) )

  def rgt[A, B](pair: Pair[A, B]): Either[A, B] =
    pair( (_, b) => Right(b) )

  val p = pair(12, "strыng")

  println(lft(p))
  println(rgt(p))
}
