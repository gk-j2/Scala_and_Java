package fintech.lecture.examples

import scala.io.StdIn

// calculator try 5 / list
object Example09CalculatorList extends App {
  sealed trait Expr[T]
  case class Mul[T](left: Expr[T], right: Expr[T]) extends Expr[T]
  case class Div[T](left: Expr[T], right: Expr[T]) extends Expr[T]
  case class Plus[T](left: Expr[T], right: Expr[T]) extends Expr[T]
  case class Minus[T](left: Expr[T], right: Expr[T]) extends Expr[T]
  case class Val[T](value: List[T]) extends Expr[T]
  case class If[T](iff: T => Boolean, cond: Expr[T], left: Expr[T], right: Expr[T]) extends Expr[T]

  object Val {
    def apply[T](xs: T*) = new Val(xs.toList)
  }

  class EvalListIntegral[T: Integral] {
    private implicit def ops(x: T): Integral[T]#IntegralOps = implicitly[Integral[T]].mkNumericOps(x)

    // change signature and add zero check
    def div(left: T, right: T): Option[T] = if(right == 0) None else Some(left / right)

    def mul(left: T, right: T) = left * right

    def plus(left: T, right: T) = left + right

    def minus(left: T, right: T) = left - right

    // we should change signature, cause right and left and cond are lists now
    // also we should change "if"
    def ifff(iff: T => Boolean, cond: List[T], left: => List[T], right: => List[T]) =
      cond.map(iff).flatMap(x => if(x) left else right)

    def calculate(expr: Expr[T]): List[T] = expr match {
      case Div(left, right) => calculate(left).flatMap{r => calculate(right).flatMap(l => div(r, l))}
      case Mul(left, right) => calculate(left).flatMap{r => calculate(right).map(l => mul(r, l))}
      case Plus(left, right) => calculate(left).flatMap{r => calculate(right).map(l => plus(r, l))}
      case Minus(left, right) => calculate(left).flatMap{r => calculate(right).map(l => minus(r, l))}
      case Val(x) => x
      case If(iff, cond, left, right) => ifff(iff, calculate(cond), calculate(left), calculate(right))
    }
  }

  def inputSize = StdIn.readInt()
  def conditionVal = Val((1 to inputSize).map(_ => StdIn.readInt.toDouble):_*)

  implicit val integ: Integral[Double] = Numeric.DoubleAsIfIntegral

  println(
    new EvalListIntegral[Double].calculate(
      If[Double](
        (_ > 3),
        conditionVal,
        Plus(Val(1,2), Div(Val(3,4), Val(0,1))),
        Plus(Val(10,20,40), Div(Val(3,4), Val(3,1)))
      )
    ))
}
