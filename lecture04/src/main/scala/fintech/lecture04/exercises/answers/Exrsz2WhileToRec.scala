package fintech.lecture04.exercises.answers
import scala.annotation.tailrec

object Exrsz2WhileToRec extends App {
  def fff(n: Int) = {
    @tailrec
    def iter(x: Int, n1: Int, n2: Int): Int =
      if (x == 0) n1
      else if (x == 1) n2
      else iter(x - 1, n2, n1 + n2)

    iter(n, 0, 1)
  }

  (0 until 10).foreach(x => println(fff(x)))
}
