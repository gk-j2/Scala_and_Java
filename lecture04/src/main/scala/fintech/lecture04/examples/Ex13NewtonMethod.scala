package fintech.lecture04.examples

import scala.annotation.tailrec

object Ex13NewtonMethod {
  object Sqrt1 {
    private def isGoodEnough(guess: Double, x: Double): Boolean =
      0.001 > math.abs(guess * guess - x)

    private def average(x1: Double, x2: Double): Double =
      (x1 + x2) / 2

    private def improveGuess(guess: Double, x: Double): Double =
      average(x, x / guess)

    @tailrec
    private def sqrtIter(guess: Double, x: Double): Double = {
      if (isGoodEnough(guess, x)) guess
      else sqrtIter(improveGuess(guess, x), x)
    }

    def sqrt(x: Double) = sqrtIter(1.0, x)
  }

  object Sqrt2 {
    def sqrt(x: Double) {
      @tailrec
      def sqrtIter(guess: Double): Double = {
        def average(x1: Double, x2: Double): Double = (x1 + x2) / 2

        def isGoodEnough(guess: Double): Boolean =
          0.001 > math.abs(guess * guess - x)

        def improveGuess(guess: Double): Double = average(x, x / guess)

        if (isGoodEnough(guess)) guess
        else sqrtIter(improveGuess(guess))
      }
      sqrtIter(1.0)
    }
  }
}
