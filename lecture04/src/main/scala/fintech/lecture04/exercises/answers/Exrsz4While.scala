package fintech.lecture04.exercises.answers
import scala.annotation.tailrec

object Exrsz4While extends App {
  @tailrec
  def dooowhile[T](start: T)(body: T => T)(p: T => Boolean): T = {
    val res = body(start)
    if (p(res)) dooowhile(res)(body)(p) else res
  }

  dooowhile(0){ i =>
    println(i)
    i + 1
  }(_ <= 3)
}
