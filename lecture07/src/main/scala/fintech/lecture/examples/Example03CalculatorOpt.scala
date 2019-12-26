package fintech.lecture.examples

import scala.io.StdIn

// calculator try 2/option
object Example03CalculatorOpt extends App {
  sealed trait Expr[T]
  case class Mul[T](left: Expr[T], right: Expr[T]) extends Expr[T]
  case class Div[T](left: Expr[T], right: Expr[T]) extends Expr[T]
  case class Plus[T](left: Expr[T], right: Expr[T]) extends Expr[T]
  case class Minus[T](left: Expr[T], right: Expr[T]) extends Expr[T]
  case class Val[T](value: T) extends Expr[T]
  case class If[T](iff: T => Boolean, cond: Expr[T], left: Expr[T], right: Expr[T]) extends Expr[T]

  class EvalOptIntegral[T: Integral] {
    private implicit def ops(x: T): Integral[T]#IntegralOps = implicitly[Integral[T]].mkNumericOps(x)

    // change signature and add zero check
    def div(left: T, right: T): Option[T] = if(right == 0) None else Some(left / right)

    def mul(left: T, right: T) = left * right

    def plus(left: T, right: T) = left + right

    def minus(left: T, right: T) = left - right

    // we should change signature, cause right and left and cond are options now
    // also we should change "if"
    def ifff(iff: T => Boolean, cond: Option[T], left: => Option[T], right: => Option[T]) =
      cond.map(iff).flatMap(x => if(x) left else right)

    def calculate(expr: Expr[T]): Option[T] = expr match {
      case Div(left, right) => calculate(left).flatMap{r => calculate(right).flatMap(l => div(r, l))}
      case Mul(left, right) => calculate(left).flatMap{r => calculate(right).map(l => mul(r, l))}
      case Plus(left, right) => calculate(left).flatMap{r => calculate(right).map(l => plus(r, l))}
      case Minus(left, right) => calculate(left).flatMap{r => calculate(right).map(l => minus(r, l))}
      case Val(x) => Some(x)
      case If(iff, cond, left, right) => ifff(iff, calculate(cond), calculate(left), calculate(right))
    }
  }

  def conditionVal = Val[Int](StdIn.readInt())

  println(
    new EvalOptIntegral[Int].calculate(
      If[Int](
        (_ > 3),
        conditionVal,
        Plus[Int](Val(1), Div(Val(3), Val(0))),
        Val(42)
      )
    ))
}
