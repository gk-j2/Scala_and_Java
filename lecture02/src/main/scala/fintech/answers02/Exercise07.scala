package fintech.answers02

case class Meter(amount: Double) extends AnyVal {

  def *(other: Meter): SqMeter = SqMeter(amount * other.amount)

  def *(mult: Int): Meter = Meter(amount * mult)

  def +(other: Meter): Meter = Meter(amount + other.amount)
}

case class SqMeter(amount: Double) extends AnyVal {

  def +(other: SqMeter): SqMeter = SqMeter(amount + other.amount)

  def *(mult: Double): SqMeter = SqMeter(amount * mult)
}

object SqMeter {

  def sqRangeSum(from: Int, to: Int): SqMeter =
    (from to to).map(SqMeter(_)).foldLeft(SqMeter(0))(_ + _)

    // (from to to).foldLeft(SqMeter(0))(_ + SqMeter(_))
}
