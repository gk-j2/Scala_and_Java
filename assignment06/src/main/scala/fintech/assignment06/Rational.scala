package fintech.assignment06

/**
 * Запрещается изменять уже определенные методы. При необходимости можно добавлять новые.
 */
final case class Rational(numerator: Int, denumerator: Int) {

  def this(i: Int) = this(i, 1)

  def +(other: Rational) =
    (this.numerator * other.denumerator + this.denumerator * other.numerator) / (this.denumerator * other.denumerator)

  def *(other: Rational) =
    (this.numerator * other.numerator) / (this.denumerator * other.denumerator)

  def abs = Rational(numerator.abs, denumerator.abs)

  def unary_- = Rational(-numerator, denumerator)
}
