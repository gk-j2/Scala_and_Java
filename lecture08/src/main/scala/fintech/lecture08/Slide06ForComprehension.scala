package fintech.lecture08

object Slide06Example1 {

  case class Film(
    name: String,
    releaseYear: Int,
    rate: Option[Double]
  )

  case class Director(
    name: String,
    birthYear: Int,
    films: List[Film]
  )


  val highPlainsDrifter = new Film("High Plains Drifter", 1973, Some(7.7))
  val outlawJoseyWales = new Film("The Outlaw Josey Wales", 1976, Some(7.9))
  val unforgiven = new Film("Unforgiven", 1992, None)

  val predator = new Film("Predator", 1987, Some(7.9))
  val dieHard = new Film("Die Hard", 1988, None)

  val eastwood = new Director("Clint Eastwood", 1930, List(highPlainsDrifter, outlawJoseyWales, unforgiven))
  val mcTiernan = new Director("John McTiernan", 1951, List(predator, dieHard))

  val directors = List(eastwood, mcTiernan)
}

object Slide06Example2 {
  import Slide06Example1._


  val result1 = directors
    .filter(_.films.size > 2)
    .flatMap { d => d.films.map { f => (d.name, f.name) } }


  val result2 = directors
    .withFilter(_.films.size > 2)
    .flatMap { d => d.films.map { f => (d.name, f.name) } }
}

object Slide06Example3 {
  import Slide06Example1._

  val result = for {
    d <- directors if d.films.size > 2
    f <- d.films
  } yield (d.name, f.name)
}

object Slide06Example4 {
  import Slide06Example1._

  // for {
  //   seq
  // } yield expr


  for {
    d <- directors           // generator
    films = d.films          // definition
    if films.size > 2        // filter
    f <- films               // generator
  } yield (d.name, f.name)

}

object Slide06Example5 {
  // for {
  //   value1 <- expr1
  // } yield expr2

  // is equivalent to 

  // expr1.map(value1 => expr2)
}

object Slide06Example6 {

  // for {
  //   value1 <- expr1 if expr2
  // } yield expr3

  // is equivalent to 

  // for {
  //   value1 <- expr1.withFilter(value1 => expr2)
  // } yield expr3

  // is equivalent to 

  // expr1.withFilter(value1 => expr2).map(value1 => expr3)
}

object Slide06Example7 {

  // for {
  //   value1 <- expr1
  //   value2 <- expr2
  //   ...
  // } yield expr3

  // is equivalent to 

  // expr1.flatMap { value1 =>
  //   for {
  //     value2 <- expr2
  //     ...
  //   } yield expr3
  // }

  // is equivalent to 

  // expr1.flatMap { value1 => expr2.map { value2 => expr3 } }
}

object Slide06Example8 {
  object Extractor {
    def unapply(any: Any): Option[Any] = ???
  }
  
  
  // for {
  //   Extractor(val1, val2) <- expr1
  // } yield expr2

  // is replaced by

  // expr1 withFilter {
  //   case Extractor(val1, val2) => true
  //   case _ => false
  // } map {
  //   case Extractor(val1, val2) => expr2
  // }
}

object Slide06Example9 {
  // for {
  //   value1 <- expr1
  //   value2 = expr2
  //   ...
  // } yield expr3

  // is replaced by

  // for {
  //   (value1, value2) <- expr1.map(value1 => (value1, expr2))
  //   ...
  // } yield expr3
}

object Slide06Example10 {
  import Slide06Example1._

  val result = directors.flatMap { director =>
    director.films.filter { film =>
      film.releaseYear == 1976 && film.rate.exists(_ > 7)
    } map { film =>
      (director.name, film.name)
    }
  }
}

object Slide06Example11 {
  import Slide06Example1._

  val result = for {
    director <- directors
    Film(name, year, Some(rate)) <- director.films if year == 1976 && rate > 7
  } yield (director.name, name)
}

object Slide06Example12 {
  import Slide06Example1._

  val result = for {
    director <- directors
    knownRates = director.films collect { case Film(_, _, Some(rate)) => rate }
    avRate = knownRates.sum / knownRates.size
    Film(name, year, Some(rate)) <- director.films if rate >= avRate
  } yield (director.name, name)
}
