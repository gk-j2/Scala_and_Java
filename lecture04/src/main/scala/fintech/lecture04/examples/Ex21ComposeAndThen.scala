package fintech.lecture04.examples

object Ex21ComposeAndThen extends App {
  val f = (x: Int) =>  s"$x$x$x"
  val func2 = (_: String).toInt

  val compose: Int => Int = func2.compose(f)
  val composeExploded = { x: Int =>
    val x1 = f(x) // apply argument (func1)
    func2(x1)         // apply self (func2)
  }


  val andThen: Int => Int = f.andThen(func2)
  val andThenExploded = { x: Int =>
    val x1 = f(x) // apply self (func1)
    func2(x1)         // apply argument (func2)
  }

  println(compose(42))
  println(andThen(13))
}


// f
// g
// f compose g == f(g(_))
// f andThen g == g(f(_))