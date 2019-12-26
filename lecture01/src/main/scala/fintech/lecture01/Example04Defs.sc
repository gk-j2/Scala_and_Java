def square(x: Int): Int = x * x

def complexStuff(x: Int) = {
  def multiply(m: Int) = x * m
  def increase(x: Int) = x + 500

  increase(multiply(1000))
}

complexStuff(100)

val repeat = {(x: Int, y: String) =>
  y * x
}

repeat(10, "foo")

def twice(x : =>String): String = {
  println("in twice")
  x*2
}

lazy val lzy = {
  println("lzy init")
  " so lazy "
}

twice(lzy)


