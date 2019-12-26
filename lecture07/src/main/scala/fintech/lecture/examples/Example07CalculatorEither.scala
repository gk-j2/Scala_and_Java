package fintech.lecture.examples

import scala.io.StdIn
import scala.util.Try

// calculator try 4/either
object Example07CalculatorEither extends App {

  sealed trait Expr[T]

  case class Mul[T](left: Expr[T], right: Expr[T]) extends Expr[T]

  case class Div[T](left: Expr[T], right: Expr[T]) extends Expr[T]

  case class Plus[T](left: Expr[T], right: Expr[T]) extends Expr[T]

  case class Minus[T](left: Expr[T], right: Expr[T]) extends Expr[T]

  class Val[T](val v: () => T) extends Expr[T] {
    def value: T = v()
  }

  object Val {
    def apply[T](x: T) = new Val(() => x)
    def apply[T](x: () => T) = new Val(x)
    def unapply[T](x: Val[T]): Option[T] = Some(x.value)
  }

  case class If[T](iff: T => Boolean, cond: Expr[T], left: Expr[T], right: Expr[T]) extends Expr[T]

  class EvalEitherIntegral[T: Integral] {
    def biMap[L1, R1, L2, R2](e: Either[L1, R1])(fl: L1 => L2)(fr: R1 => R2): Either[L2, R2] = e match {
      case Left(l) => Left[L2, R2](fl(l))
      case Right(r) => Right[L2, R2](fr(r))
    }

    private implicit def ops(x: T): Integral[T]#IntegralOps = implicitly[Integral[T]].mkNumericOps(x)

    // change signature
    def div(left: T, right: T): Either[String, T] = biMap(Try(left / right).toEither)(_.toString)(identity)

    def mul(left: T, right: T) = left * right

    def plus(left: T, right: T) = left + right

    def minus(left: T, right: T) = left - right

    // we should change signature, cause right and left and cond are eithers now
    // also we should change "if"
    def ifff(iff: T => Boolean, cond: Either[String, T], left: => Either[String, T], right: => Either[String, T]) =
      cond.map(iff).flatMap(x => if (x) left else right)

    def calculate(expr: Expr[T]): Either[String, T] = expr match {
      case Div(left, right) => calculate(left).flatMap { r => calculate(right).flatMap(l => div(r, l)) }
      case Mul(left, right) => calculate(left).flatMap { r => calculate(right).map(l => mul(r, l)) }
      case Plus(left, right) => calculate(left).flatMap { r => calculate(right).map(l => plus(r, l)) }
      case Minus(left, right) => calculate(left).flatMap { r => calculate(right).map(l => minus(r, l)) }
      case v: Val[T] => biMap(Try(v.v()).toEither)(_.toString)(identity)
      case If(iff, cond, left, right) => ifff(iff, calculate(cond), calculate(left), calculate(right))
    }
  }

  def conditionValF = Val[Int](() => StdIn.readInt())

  println(
    new EvalEitherIntegral[Int].calculate(
      If[Int](
        (_ > 3),
        conditionValF,
        Plus[Int](Val(1), Div(Val(3), Val(0))),
        Val(42)
      )
    ))
}
