package fintech.lecture04.examples

object Ex01FuncDef extends App {
  def funky1(foo: String, bar: Int): String = {
    foo + bar
  }

  def funky2(foo: String, bar: Int): Unit = {
    foo + bar
  }

  private def funky3(foo: String, bar: Int) = {
    foo + bar
  }

  //depecated
  private def funky4(foo: String, bar: Int) = {
    foo + bar
  }

  println(funky1("abcd", 5))
  println(funky2("abcd", 5))
  println(funky3("abcd", 5))
  println(funky4("abcd", 5))
}
