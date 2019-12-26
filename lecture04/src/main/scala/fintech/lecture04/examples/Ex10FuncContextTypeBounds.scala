package fintech.lecture04.examples

object Ex10FuncContextTypeBounds extends App {
  def sortYa0[T](xs: Seq[T])(implicit ord: Ordering[T]) = {
    xs.sorted(ord.reverse)
  }

  def sortYa1[T: Ordering](xs: Seq[T]) = {
    xs.sorted(implicitly[Ordering[T]].reverse)
  }


  val ordering = implicitly[Ordering[Int]] // it comes from predef

  println(sortYa0(List(1,2,3,4,5,6))(ordering))
  println(sortYa0(List(1,2,3,4,5,6)))
  println(sortYa1(List(1,2,3,4,5,6))(ordering))
  println(sortYa1(List(1,2,3,4,5,6)))
}
