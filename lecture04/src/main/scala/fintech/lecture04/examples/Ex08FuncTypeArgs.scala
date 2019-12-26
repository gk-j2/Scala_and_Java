package fintech.lecture04.examples

object Ex08FuncTypeArgs extends App {
  def identityAny(a: Any): Any = a
  def identityStr(a: String): String = a
  def identityInt(a: Int): Int = a
  //etc

  def identity[T](a: T): T = a
}
