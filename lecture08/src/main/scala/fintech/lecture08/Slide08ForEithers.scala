package fintech.lecture08

object Slide08Example1 {
  import Slide06Example1._

  def find[A](xs: List[A], pred: A => Boolean, error: => String): Either[String, A] =
    xs.find(pred) match {
      case Some(x) => Right(x)
      case None => Left(error)
    }

  val film = for {
    eastwood <- find(directors, (d: Director) => d.name == "Clint Eastwood", "Unknown director")
    unforgiven <- find(eastwood.films, (f: Film) => f.name == "Unforgiven", "Unknown film")
  } yield unforgiven
}
