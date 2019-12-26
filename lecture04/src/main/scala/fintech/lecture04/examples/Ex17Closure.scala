package fintech.lecture04.examples

object Ex17Closure extends App {
  var counter = 0

  val count0: () => Unit = {() =>
    counter += 1
  }
  //the same as count0
  val count = {() =>
    counter += 1
  }

  List(1,2,3,4,5).foreach(_ => count())
  println(counter)
}