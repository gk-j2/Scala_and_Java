package fintech.assignment02

/**
 * Написать класс, описывающий комплексные числа.
 *
 * Реализовать проверку на равенство, умножение и сложение, toString.
 * Реализовать оператор возведения в целую степень: "~".
 *
 * Подумайте почему мы определили метод equals со своей сигнатурой, а
 * не воспользовались стандартным методом.
 */
case class ComplexNumber(re: Double, im: Double) {

  def +(that: ComplexNumber): ComplexNumber = {
    val res = new ComplexNumber(this.re + that.re, this.im + that.im)
    res
  }

  def -(that: ComplexNumber): ComplexNumber = {
    val res = new ComplexNumber(this.re - that.re, this.im - that.im)
    res
  }

  def *(that: ComplexNumber): ComplexNumber = {
    val res = new ComplexNumber((this.re * that.re - this.im * that.im), (this.re * that.im + that.re * this.im))
    res
  }

  def /(that: ComplexNumber): ComplexNumber = {
    lazy val res = new ComplexNumber((this.re * that.re + this.im * that.im) / (that.re * that.re + that.im * that.im), (that.re * this.im - this.re * that.im) / (that.re * that.re + that.im * that.im))
    if ((that.re * that.re + that.im * that.im) >= 0.0000000000001)
      res
    else throw new Exception("Division by zero")
  }

  def ~(n: Int): ComplexNumber = {
    def mod(r: Double, i: Double): Double = {
      val res = math.sqrt(r*r + i*i)
      res
    }
    def arg(r: Double, i: Double): Double = {
      if (r == 0 && i > 0) math.Pi / 2
      else if (r == 0 && i < 0) (-1) * math.Pi / 2
      else if (r > 0) math.atan(i/r)
      else if (r < 0 && i > 0) math.Pi + math.atan(i/r)
      else (-1) * math.Pi + math.atan(i/r)
    }
    val len = mod(this.re, this.im)
    val phasa = arg(this.re, this.im)
    if (len <= 0.000000000001 && n < 0) throw  new Exception("Small number in negativ power")
    else if (len >= 0.000000000001) ComplexNumber(math.pow(len, n) * scala.math.cos(n * phasa), (math.pow(len, n) * scala.math.sin(n * phasa)))
    else ComplexNumber(0,0)
  }

  def equals(that: ComplexNumber, tolerance: Double): Boolean = {
    (math.abs(this.re - that.re) <= tolerance && math.abs(this.im - that.im) <= tolerance)
  }

  override def toString: String = {s"${this.re} + i * ${this.im}"}
}
