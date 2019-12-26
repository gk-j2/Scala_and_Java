package fintech.lecture08

object Slide07Example1 {
  import Slide06Example1._

  val film = for {
    eastwood <- directors.find(_.name == "Clint Eastwood")
    unforgiven <- eastwood.films.find(_.name == "Unforgiven")
  } yield unforgiven

  val none = for {
    eastwood <- directors.find(_.name == "WrongName")
    unforgiven <- eastwood.films.find(_.name == "Unforgiven")
  } yield unforgiven
}
