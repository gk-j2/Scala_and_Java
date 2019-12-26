package fintech.lecture06

object Example08ImplicitParametersOrder {

  sealed trait BaseSought

  class Target extends BaseSought

  class Alternative extends BaseSought


  trait Searchable[T <: BaseSought]


  implicit def search[T <: BaseSought](implicit sought: T, canSearch: Searchable[T]): T = sought
  implicit def searchGood[T <: BaseSought](implicit canSearch: Searchable[T], sought: T): T = sought


  implicit val target = new Target() // ambiguity
  implicit val alt = new Alternative() // ambiguity

  implicit val canSearchTarget = new Searchable[Target] {}

  //ambiguous
  //search

  // canSearch narrows implicit search to T == Target
  searchGood
}
