package fintech.lecture11

case class Writer[M,T](log: List[M], value: T)

object Writer {
  def single[M,T](value: T, message: M) = new Writer(List(message), value)
}