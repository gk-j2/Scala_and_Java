package fintech.lecture.examples

import scala.io.StdIn
import scala.util.Try

// calculator try 3/try
object Example05CalculatorTry extends App {
  sealed trait Expr[T]
  case class Mul[T](left: Expr[T], right: Expr[T]) extends Expr[T]
  case class Div[T](left: Expr[T], right: Expr[T]) extends Expr[T]
  case class Plus[T](left: Expr[T], right: Expr[T]) extends Expr[T]
  case class Minus[T](left: Expr[T], right: Expr[T]) extends Expr[T]

  //how we can reach same effect without functional value?
  class Val[T](val v: () => T) extends Expr[T] {
    def value: T = v()
  }

  object Val {
    def apply[T](x: T) = new Val(() => x)
    def apply[T](x: () => T) = new Val(x)
    def unapply[T](x: Val[T]): Option[T] = Some(x.value)
  }
  case class If[T](iff: T => Boolean, cond: Expr[T], left: Expr[T], right: Expr[T]) extends Expr[T]

  class EvalTryIntegral[T: Integral] {
    private implicit def ops(x: T): Integral[T]#IntegralOps = implicitly[Integral[T]].mkNumericOps(x)

    // change signature
    def div(left: T, right: T): Try[T] = Try(left / right)

    def mul(left: T, right: T) = left * right

    def plus(left: T, right: T) = left + right

    def minus(left: T, right: T) = left - right

    // we should change signature, cause right and left and cond are trys now
    // also we should change "if"
    def ifff(iff: T => Boolean, cond: Try[T], left: => Try[T], right: => Try[T]) =
      cond.map(iff).flatMap(x => if(x) left else right)

    def calculate(expr: Expr[T]): Try[T] = expr match {
      case Div(left, right) => calculate(left).flatMap{r => calculate(right).flatMap(l => div(r, l))}
      case Mul(left, right) => calculate(left).flatMap{r => calculate(right).map(l => mul(r, l))}
      case Plus(left, right) => calculate(left).flatMap{r => calculate(right).map(l => plus(r, l))}
      case Minus(left, right) => calculate(left).flatMap{r => calculate(right).map(l => minus(r, l))}
      case v: Val[T] => Try(v.v())
      case If(iff, cond, left, right) => ifff(iff, calculate(cond), calculate(left), calculate(right))
    }
  }

  def conditionValF = Val[Int](() => StdIn.readInt())

  println(
    new EvalTryIntegral[Int].calculate(
      If[Int](
        (_ > 3),
        conditionValF,
        Plus[Int](Val(1), Div(Val(3), Val(0))),
        Val(42)
      )
    ))
}
