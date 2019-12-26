package fintech.lecture04

object Test extends App {
  def foo[T, U <: T](x: T, y: =>U, useFallback: Boolean) =
    if(useFallback) y
    else x



  // 1.

  println(foo(12, {
    println("some")
    "fall"
  }, true))

  // что наепчатает вызов?



  // 2.

  val xxx1: Function1[Double, Function1[Nothing, Function1[Boolean, Double]]] =
    (foo[Double, Nothing] _).curried

  val xxx11: Function3[Double, Nothing, Boolean, Double] = ???
  val xxx12: Function1[Double, Function1[Nothing, Function1[Boolean, Double]]] = ???
  val xxx13: Function3[Nothing, Double, Boolean, Double] = ???

  // каков тип xxx?



  // 3.

  val xxx2 = {y: Double =>
    (foo[Double, Nothing] _).curried.andThen { x =>
      x(throw new Exception("42"))(false)
    }
  }

  // каков тип xxx2 ??

  val res: Function1[Double, Double] = xxx2(12)
  println(res(23))
}
