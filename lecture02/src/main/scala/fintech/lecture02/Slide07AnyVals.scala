package fintech.lecture02

object Slide07Example1 {

  class Dollar(val amount: Int) extends AnyVal {
    override def toString() = amount + "$"
  }

}

object Slide07Example2 {
  import Slide07Example1._

  class Euro(val amount: Int) extends AnyVal {

    def +(other: Euro) = new Euro(amount + other.amount)

    override def toString() = amount + "Euro"
  }


  val oneDollar = new Dollar(1)
  val oneEuro = new Euro(1)

  oneEuro + oneEuro

//   oneEuro + oneDollar
  // compilation error
}

object Slide07Example3 {

  trait Currency extends Any {
    def asString: String
  }

  case class Dollar(val amount: Int) extends Currency {
    override def asString = amount + "$"
  }

  def printCurrency(c: Currency) = println(c.asString)

  printCurrency(Dollar(123))
}
