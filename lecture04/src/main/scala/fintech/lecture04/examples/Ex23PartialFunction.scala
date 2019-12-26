package fintech.lecture04.examples

object Ex23PartialFunction extends App {
  // not partial
  val oddbad: Int => Int = {
    case x if x % 2 == 1 =>
      x * 3
  }

  //partial
  val odd: PartialFunction[Int, Int] = {
    case x if x % 2 == 1  =>
      x * 3
  }

  //syntax sugar
  val even: PartialFunction[Int,Int]  = {
    case x if x % 2 == 0  =>
      x / 2
  }

  //partial by hand
  val evenByHand = new PartialFunction[Int,Int] {
    override def isDefinedAt(x: Int): Boolean = x % 2 == 0
    override def apply(arg: Int): Int = arg match {
      case x if x % 2 == 0  => x / 2
    }
  }

  println(odd(2))
  println(even(3))
  // wtf???
  println(evenByHand(3))

  val lifted: Int => Option[Int] = even.lift
  even.orElse(odd)
}
