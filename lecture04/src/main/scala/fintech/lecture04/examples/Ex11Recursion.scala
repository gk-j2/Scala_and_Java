package fintech.lecture04.examples

import scala.annotation.tailrec

object Ex11Recursion {
  // WOW, so much state to control!
  def factorialCycle(n: Int): Int = {
    var res = 1
    var cnt = 1
    while (cnt <= n) {
      res = res * cnt
      cnt += 1
    }
    res
  }

  def factorialRecurse(n: Int): Int =
    if (n == 0) 1
    else factorialRecurse(n - 1) * n



  /**
   *     tailrec try #1
   **/

//  @tailrec
  def factorial1(n: Int): Int =
    if (n == 0) 1
    else factorial1(n - 1) * n


  /**
    *     tailrec try #2
    **/

//    @tailrec
  def factorial2(n: Int): Int =
    if (n == 0) 1
    else n * factorial2(n - 1)

  /**
    *     tailrec success
    **/

  @tailrec
  private def factorialAcc(acc: Int, n: Int): Int =
    if (n == 0) acc
    else factorialAcc(acc * n, n - 1)

  def factorial3(n: Int) = factorialAcc(1, n)
}