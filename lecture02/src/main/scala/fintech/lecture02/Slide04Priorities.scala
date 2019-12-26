package fintech.lecture02

object Slide04Example1 {

  case class A(value: Int) {
    def +++(other: A): A = ???

    def ***(other: A): A = ???
  }

  val a = A(1)
  val b = A(2)
  val c = A(3)


  a +++ b *** c

}

object Slide04Example2 {

  2 << 2 + 2
}

object Slide04Example3 {

  var x = 1
  val y = 2

  x *= y + 1
}

object Slide04Example4 {
  val a = 1
  val b = 2
  val c = 3

  a * b
  // is equivalent to
  a.*(b)

  val as = List(1, 2, 3)
  val bs = List(4, 5)
  val cs = List(6)

  as ::: bs
  // is equivalent to
  bs.:::(as)

  // a * b * c
  //   ->
  // (a * b) * c
  //   ->
  // (a.*(b)).*(c)
  //

  // a ::: b ::: c
  //   ->
  // a ::: (b ::: c)
  //   ->
  // (b ::: c).:::(a)
  //   ->
  // (c.:::(b)).:::(a)
}

object Slide04Example5 {
  lazy val a = {
    println("")
    List(1, 2, 3)
  }
  lazy val b = List(4, 5)

  a ::: b

  // is equivalent to block

  val x = a
  b.:::(x)
}
