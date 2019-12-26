package fintech.lecture06

object Example05Usage2 extends App {

  case class Dollar(amount: Double)

  class DollarOps(val dollar: Dollar) extends AnyVal {
    def double = Dollar(dollar.amount * 2)
  }

  implicit def dollar2dollarOps(dollar: Dollar) = new DollarOps(dollar)

  println(Dollar(1d).double)

  // is replaced by
  //
  // println(dollar2dollarOps(Dollar(1d)).double)
}
