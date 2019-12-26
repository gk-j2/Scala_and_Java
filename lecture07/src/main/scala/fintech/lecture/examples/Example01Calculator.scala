package fintech.lecture.examples

import scala.io.StdIn

// calculator try 1
object Example01Calculator extends App {
  sealed trait Expr[T]
  case class Mul[T](left: Expr[T], right: Expr[T]) extends Expr[T]
  case class Div[T](left: Expr[T], right: Expr[T]) extends Expr[T]
  case class Plus[T](left: Expr[T], right: Expr[T]) extends Expr[T]
  case class Minus[T](left: Expr[T], right: Expr[T]) extends Expr[T]
  case class Val[T](value: T) extends Expr[T]
  case class If[T](iff: T => Boolean, cond: Expr[T], left: Expr[T], right: Expr[T]) extends Expr[T]

  class EvalIntegral[T: Integral] {
    private implicit def ops(x: T): Integral[T]#IntegralOps = implicitly[Integral[T]].mkNumericOps(x)

    def div(left: T, right: T) = left / right

    def mul(left: T, right: T) = left * right

    def plus(left: T, right: T) = left + right

    def minus(left: T, right: T) = left - right

    def iff(iff: T => Boolean, cond: T, left: => T, right: => T) = if (iff(cond)) left else right

    def calculate(expr: Expr[T]): T = expr match {
      case Div(left, right) => calculate(left) / calculate(right)
      case Mul(left, right) => calculate(left) * calculate(right)
      case Plus(left, right) => calculate(left) + calculate(right)
      case Minus(left, right) => calculate(left) - calculate(right)
      case Val(x) => x
      case If(iff, cond, left, right) => if (iff(calculate(cond))) calculate(left) else calculate(right)
    }
  }

  def conditionVal = Val[Int](StdIn.readInt())

  println(
    new EvalIntegral[Int].calculate(
    If[Int](
      (_ > 3),
      conditionVal,
      Plus[Int](Val(1), Div(Val(3), Val(0))),
      Val(42)
    )
  ))
}
