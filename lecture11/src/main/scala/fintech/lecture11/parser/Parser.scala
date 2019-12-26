package fintech.lecture11.parser


// parse input string to all possible values A and rest of string
final case class Parser[A](run: String => List[(A, String)])

object Parser {
  def parse[A](p: Parser[A], s: String) = p run s
}