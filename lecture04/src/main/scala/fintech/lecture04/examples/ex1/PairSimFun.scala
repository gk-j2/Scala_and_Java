package org.urfu.scalafp.lection04.ex1

object PairSimFun extends App {

  type Pair[A] = ((A, A) => A) => A

  def pair[A](a: A, b: A): Pair[A] =  m => m(a, b)

  def lft[A](pair: Pair[A]): A =
    pair( (a, _) => a )

  def rgt[A](pair: Pair[A]): A =
    pair( (_, b) => b )

  val p = pair("foo", "bar")

  println(lft(p))
  println(rgt(p))
}
