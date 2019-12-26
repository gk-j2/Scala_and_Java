package fintech.lecture11.parser

import fintech.lecture11._

object ParsersInstances {
  import Parser._

  implicit val parserMonad: Monad[Parser] = new Monad[Parser] {
    override def unit[A](a: A): Parser[A] = Parser(inp => List((a, inp)))

    override def flatMap[A, B](p: Parser[A])(f: A => Parser[B]): Parser[B] = Parser(inp =>
      for {
        (v, out)  <- parse(p, inp)
        res       <- parse(f(v), out)
      } yield res
    )
  }

  implicit val parserAlternative: Alternative[Parser] = new Alternative[Parser] {
    // always failing (empty) parser
    override def empty[A]: Parser[A] = Parser(ignored => List.empty)

    override def <|>[A](p: Parser[A], q: Parser[A]): Parser[A] = Parser(inp =>
      parse(p, inp) match {
        case Nil => parse(q, inp)
        case xs  => xs
      }
    )
  }
}