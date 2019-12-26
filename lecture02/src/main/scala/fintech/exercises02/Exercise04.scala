package fintech.exercises02

case class MyInt(val i: Int) {

  def :+(mi: MyInt): MyInt = MyInt(i + mi.i)

  def :-(mi: MyInt): MyInt = MyInt(i - mi.i)

  def ::(mi: MyInt): MyInt = MyInt(i - 2 * mi.i)

  def +=(mi: MyInt): MyInt = MyInt(i + 2 * mi.i)

  def &&(mi: MyInt): MyInt = MyInt(i)

  def ||(mi: MyInt): MyInt = MyInt(mi.i)

  override def toString = i.toString
}

/**
 * Вычислите значения выражений
 */
object MyInt extends App {

  def exp1 = MyInt(1) :+ MyInt(2) :+ MyInt(3)

  def exp2 = MyInt(1) :: MyInt(2) :: MyInt(3)

  def exp3 = MyInt(1) :- MyInt(2) += MyInt(3) :: MyInt(2) :: MyInt(1)

  def exp4 = MyInt(2) && MyInt(1) || MyInt(1) && MyInt(5) += MyInt(2)

  println(exp1)
}
