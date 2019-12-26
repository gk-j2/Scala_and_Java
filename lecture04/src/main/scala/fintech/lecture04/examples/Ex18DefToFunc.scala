package fintech.lecture04.examples

object Ex18DefToFunc extends App {

  class Molly {
    var z = 0.0

    def qwerty(x: Int, y: Double): Double = z + x / y
  }

  val mlly = new Molly()
  val qFun1: (Int, Double) => Double = mlly.qwerty
  val qFun2 = mlly.qwerty _

  println(qFun1 + "    res    " + qFun1(42, 14.0))

  mlly.z = 122
  println(qFun2 + "    res    " + qFun2(42, 14.0))
}
