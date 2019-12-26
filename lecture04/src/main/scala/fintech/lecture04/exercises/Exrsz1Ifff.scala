package fintech.lecture04.exercises

object Exrsz1Ifff extends App {
  // make 'reverse' if using args by name

  def ifff[A](pred: Boolean)(lft: Unit)(rgt: Unit): Unit = {
    if (pred) lft
    else rgt
  }

  ifff(1 != 2)(
    throw new Exception("boom")
  )(
    println("42")
  )

}
