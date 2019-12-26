package org.urfu.scalafp.lection04.ex2

object FixedPoint {
  def thresh: Double = 0.0001

  def fixedPoint(f: Double => Double, init: Double): Double = {
    def isCloseEnough(x1: Double, x2: Double) = math.abs(x1 - x2) < thresh

    def iter(guess: Double): Double = {
      println(guess)
      val next = f(guess)
      if (isCloseEnough(next, guess)) guess
      else iter(next)
    }

    iter(init)
  }
}
