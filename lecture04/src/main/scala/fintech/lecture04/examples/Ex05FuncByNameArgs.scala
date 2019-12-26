package fintech.lecture04.examples

object Ex05FuncByNameArgs extends App {
  def int42 = 42

  def funky(foo: String, bar: Int = int42): String = {
    println("funky")
    foo + bar
  }

  def funkyByName(foo: =>String): String = {
    println("funkyByName 1")
    println(foo)
    println("funkyByName 2")
    foo
  }

  println("res1 :" + funkyByName(funky(bar = 10 + 3, foo = "oppa")))

  println
  println("--" * 30)
  println

  println("res2 : " + funkyByName(funky(foo = "oppa")))
}
