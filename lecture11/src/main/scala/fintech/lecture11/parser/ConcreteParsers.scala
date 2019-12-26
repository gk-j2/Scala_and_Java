package fintech.lecture11.parser

import fintech.lecture11.Monad
import fintech.lecture11.Monad._
import fintech.lecture11.Functor._
import fintech.lecture11.FromMonadInstances
import ParsersInstances._
import scala.io.StdIn

object ConcreteParsers extends App {
  import Parsers._
  
  val fromMonad = new FromMonadInstances[Parser]
  import fromMonad._

  //consume three chars, drop middle char
  val three: Parser[(Char,Char)] =
    for {
      x <- item
      _ <- item
      z <- item
    } yield (x, z)


  val natsNel: Parser[List[Int]] =
    for {
      _ <- symbol("List(")
      n <- int
      ns <- many(
        for {
          _ <- symbol(",")
          x <- int
        } yield x
      )
      _ <- symbol(")")
    } yield n :: ns

    println(natsNel.run("List(1,-2,3,-4)"))
    println(natsNel.run("List()"))
    println(natsNel.run("List('a','b')"))
}