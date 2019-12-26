package org.urfu.scalafp.lection04.ex2

import scala.annotation.tailrec

object SQRT {
  //не сходится
  def sqrtInfLoop(x: Double) = FixedPoint.fixedPoint(x / (_: Double), 1.0)
  //сходится, требует ручного вывода y => (x / y + y) / 2
  def sqrtAv(x: Double) = FixedPoint.fixedPoint(y => (x / y + y) / 2, 1.0)

  //торможение усреднением
  private def avgDump(f: Double => Double) = {x: Double =>
    (x + f(x)) / 2
  }
  //используем торможение
  def sqrt(x: Double) = FixedPoint.fixedPoint(avgDump(x / (_: Double)), 1.0)

  def sqrt2F(x: Double) = x / (_: Double)
  def sqrt3F(x: Double) = {y: Double => x / (y * y)}

  def sqrt2(x: Double) = FixedPoint.fixedPoint(avgDump(sqrt2F(x)), 1.0)
  def sqrt3(x: Double) = FixedPoint.fixedPoint(avgDump(sqrt3F(x)), 1.0)

  // whe should get

  object ROOT {
    def avgDump(f: Double => Double) = {x: Double =>
      (x + f(x)) / 2
    }

    def fixpointF(x: Double, n: Int): Double => Double = {y =>
      @tailrec
      def yn(n: Int, acc: Double): Double = if (n == 2) acc
      else yn(n - 1, acc * y)

      x / yn(n, y)
    }

    def sqrt(x: Double, n: Int) = FixedPoint.fixedPoint(
      avgDump(
        fixpointF(x, n)), 1.0)
  }
}

object Calc extends App {
  import SQRT.ROOT._

  println(fixpointF(32, 5)(2))
  println(fixpointF(32, 4)(2))
  println(fixpointF(32, 3)(2))
  println(fixpointF(32, 2)(2))

  println(sqrt(2, 6))
}