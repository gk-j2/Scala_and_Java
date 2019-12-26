package fintech.lecture04.examples

object Ex06FuncVarArgs extends App {
  def sum(items: Int*): Int = {
    val itms: Seq[Int] = items
    var total = 0
    for (i <- itms) total += i
    total
  }

  println(sum(1, 2, 3, 5, 5))

  //convert Seq to vararg
  println(sum(Seq(1, 2, 3, 4, 5): _*))
}
