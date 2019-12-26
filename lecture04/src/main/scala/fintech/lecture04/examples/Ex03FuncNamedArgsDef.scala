package fintech.lecture04.examples

object Ex03FuncNamedArgsDef {
  def funky1(foo: String, bar: Int): String = {
    foo + bar
  }

  funky1(bar = 42, foo = "oppa")
}
