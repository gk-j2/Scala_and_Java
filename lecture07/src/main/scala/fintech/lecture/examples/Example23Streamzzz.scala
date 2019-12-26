package fintech.lecture.examples


object Example23Streamzzz extends App {
  val streamFinite1234 = Stream(1,2,3,4)

  val infiniteOne: Stream[Int] = 1 #:: infiniteOne
  println(infiniteOne.take(10).toList)

  def infinitePlusOne(x: Int): Stream[Int] = x #:: infinitePlusOne(x + 1)

  println(infinitePlusOne(0).take(10).toList)

  lazy val fib: Stream[Int] = {
    def loop(h: Int, n: Int): Stream[Int] = h #:: loop(n, h + n)
    loop(0, 1)
  }

  fib.take(20).foreach(println)
}
