package fintech.lecture08

object Slide02Example1 {

  sealed trait A
  final case class B() extends A
  final case class C() extends A

}
