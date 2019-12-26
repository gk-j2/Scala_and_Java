package fintech.lecture02

object Slide08Example1 extends App {

  val hello = "Hello"
  val world = "world"
  val helloWorld = hello + " " + world + "!"
  println(helloWorld)

}

object Slide08Example2 {

  "hello".capitalize

  "hello" * 3

  "hello".head

}

object Slide08Example3 extends App {

  val name = "world"
  println(s"Hello, $name!")

  println(s"Hello, ${name.capitalize}")
}

object Slide08Example4 extends App {

  val name = "world"

  println(s"Hello\\\\$name")

  println(raw"Hello\\\\$name")

}

object Slide08Example5 extends App {

  println(s"${math.Pi}%.5f")

}
