package fintech.lecture03.examples

object Example20Traits extends App {
  trait A
  trait B
  trait C extends A

  class D extends A with B with C

  trait Ordered[A] {
    def compare(that: A): Int

    def <(that: A): Boolean = this.compare(that) < 0
    def >(that: A): Boolean = this.compare(that) > 0
  }

  class Circle(private val r: Double) extends Ordered[Circle] {
    def compare(that: Circle): Int = this.r.compareTo(that.r)

    override def toString: String = s"Circle($r)"
  }

  val c1 = new Circle(1)
  val c2 = new Circle(2)

  println(s"$c1 > $c2 == ${c1 > c2}")
  println(s"$c1 < $c2 == ${c1 < c2}")
}
