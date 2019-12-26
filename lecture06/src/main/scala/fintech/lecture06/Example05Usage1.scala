package fintech.lecture06

object Example05Usage1 {

  case class Dollar(amount: Double)

  case class Euro(amount: Double)

  implicit def euroToDollar(euro: Euro): Dollar = Dollar(euro.amount * 1.13)

  val dollar: Dollar = Euro(100)

//  is replaced by
//
//  val dollar: Dollar = euroToDollar(Euro(100))
}
