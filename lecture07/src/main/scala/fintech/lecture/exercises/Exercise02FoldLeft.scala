package fintech.lecture.exercises

object Exercise02FoldLeft extends App {
  type ??? = Nothing

  def reverse[T](xs: List[T]): List[T] = xs.foldLeft(_)(_ :: )
  def filter[T](xs: List[T])(p:  => ???): List[T] = ???
  def map[T](xs: List[T])(f: Int => T): List[???] = xs.foldLeft(0)(f())
}
