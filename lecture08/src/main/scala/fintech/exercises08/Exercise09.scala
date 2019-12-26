package fintech.exercises08

object Exercise09 extends App {

  final case class Donut(size: Int)
  final case class Coffee(size: Int)

  def buyDonut: Payment[Donut] =
    Payment(10, Donut(size = 5))

  def buyCoffee(donutSize: Int): Payment[Coffee] = {
    val coffeeSize = donutSize / 2
    Payment(coffeeSize * 3, Coffee(coffeeSize))
  }

  def pay[R](payment: Payment[R]): R = {
    println(s"Making payment: ${payment.moneyAmount}")
    payment.o
  }

  // ЗАДАНИЕ: Нужно реализовать методы map и flatMap так, чтобы Payment
  // можно было использовать в for comprehension.
  case class Payment[A](moneyAmount: Int, o: A) {
    def map[A](func: A => Payment[A]): Payment[A] = {
      Payment(moneyAmount, func(o))
    }
    def flatMap[R](func: A => Payment[B]): Payment[B] = {
      val o2 = func(o)
      o2.copy(moneyAmount = o2.moneyAmount + moneyAmount)
    }
  }

  val breakfast = for {
    donut <- buyDonut
    coffee <- buyCoffee(donut.size)
  } yield (donut, coffee)

  val result = pay(breakfast)
  println(result)
}
