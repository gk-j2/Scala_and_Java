package fintech.lecture11.parser

import fintech.lecture11.Monad
import fintech.lecture11.Monad._
import fintech.lecture11.Functor._
import fintech.lecture11.Alternative
import fintech.lecture11.Alternative._
import fintech.lecture11.FromMonadInstances
import ParsersInstances._
import scala.io.StdIn

object ArithmeticExpressionParsers extends App {
  import Parsers._

  val fromMonad = new FromMonadInstances[Parser]
  import fromMonad._

  // DAT GRAMMA
  // expr   ::= term   ( + expr | - expr | E)
  // term   ::= factor ( * term | / term | E)
  // factor ::= ( expr ) | nat
  // nat    ::= 0 | 1 | 2 | ...

  def expr: Parser[Int] =
    for {
      t <- term
      res <-
        (for {
          _ <- symbol("+")
          e <- expr
        } yield t + e) <|>
          (for {
            _ <- symbol("-")
            e <- expr
          } yield t - e) <|> unit(t)
    } yield res

  def term: Parser[Int] =
    for {
      t <- factor
      res <-
        (for {
          _ <- symbol("*")
          e <- expr
        } yield t * e) <|>
          (for {
            _ <- symbol("/")
            e <- expr
          } yield t / e) <|> unit(t)
    } yield res

  def factor: Parser[Int] =
    (for {
      _ <- symbol("(")
      e <- expr
      _ <- symbol(")")
    } yield e) <|> natural

    println(expr.run("1 + 2 + (3 - 1) * 5"))
}