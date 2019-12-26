package fintech.lecture11.parser

import fintech.lecture11.Monad
import fintech.lecture11.Alternative

object Parsers {
  //import syntax
  import fintech.lecture11.Functor._
  import fintech.lecture11.Monad._
  import fintech.lecture11.Alternative._
  //import instance
  import ParsersInstances._
  import fintech.lecture11.FromMonadInstances

  val fromMonad = new FromMonadInstances[Parser]
  import fromMonad._

  // just read first letter
  val item: Parser[Char] = Parser {x => 
    x.toSeq match {
      case Seq(head, tail@_*) 
        => List((head, new String(tail.toArray)))
      case _                  
        => List.empty
    }
  }

  def unit[A](res: A): Parser[A] = Monad[Parser].unit(res)

  def sat(p: Char => Boolean): Parser[Char]  = for {
    x <- item
    res <- if (p(x)) unit(x) else empty
  } yield res

  def empty[A]: Parser[A] = Alternative[Parser].empty

  def digit: Parser[Char] = sat(_.isDigit)
  def lower: Parser[Char] = sat(_.isLower)
  def upper: Parser[Char] = sat(_.isUpper)
  def letter: Parser[Char] = sat(_.isLetter)
  def alphanum: Parser[Char] = letter <|> digit
  def char(x: Char): Parser[Char] = sat(_ == x)

  //parse whole string
  def chars(chrs: List[Char]): Parser[List[Char]] = chrs match {
    case Nil => unit(List.empty)
    case x :: xs => for {
      head <- char(x)
      tail <- chars(xs)
    } yield head :: tail
  }
  def string(str: String) = chars(str.toList).map(_.mkString)


  // this parse zero or more x repeatition
  def many[A](x: Parser[A]): Parser[List[A]] = some(x) <|> unit(List.empty[A])

  // this parse one or more x repeatition
  def some[A](x: Parser[A]): Parser[List[A]] = for {
    head <- x
    tail <- many(x)
  } yield head :: tail


  // alphanum string with lower char start
  val ident = for {
    x <- lower
    xs <- many(alphanum)
  } yield (x :: xs).mkString

  //positive natural number
  val nat = for {
    xs <- some(digit)
  } yield xs.mkString.toInt

  //signed natural number
  val int = (for {
    _ <- char('-')
    n <- nat
  } yield (-n)) <|> nat

  //spaces
  val space = for {_ <- many(sat(_.isSpaceChar))} yield ()


  //something surrounded by spaces ( token )
  def token[A](p: Parser[A]) = for {
    _ <- space
    v <- p
    _ <- space
  } yield v


  val identifier = token(ident)
  val natural = token(nat)
  val integer = token(int)
  def symbol(sym: String) = token(string(sym))
}

object ParsersApp extends App {
  import Parsers._

  println(identifier.run("ideNT123 "))
  println(identifier.run(" ideNT123 "))
  println(natural.run("42 "))
  println(natural.run(" 42 "))
  println(integer.run(" -12"))
  println(integer.run(" -12 "))
}