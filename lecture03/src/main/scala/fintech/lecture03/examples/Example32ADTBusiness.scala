package fintech.lecture03.examples

import java.time.Instant

object Example32ADTBusiness {

  class Book

  sealed trait Order
  final case class FinishedOrder(charge: BigDecimal) extends Order
  final case class DeferredOrder(id: String, books: List[Book]) extends Order

  sealed trait Shipping
  final case class Pickup(shopId: String) extends Shipping
  final case class LocalDelivery(address: String, timeFrame: TimeFrame) extends Shipping
  final case class GlobalDelivery(country: String, address: String) extends Shipping

  case class TimeFrame(from: Instant, to: Instant)

//  def orderBooks(client: String, books: List[Book]): Order =
//    if (allBooksAreAvailable(books)) {
//      ???
//      FinishedOrder(???)
//    } else {
//      val deferredOrder = storeDeferredOrder(books)
//      deferredOrder
//    }
//
//  def shipBook(books: List[Book], shipping: Shipping): Unit = shipping match {
//    case Pickup(shopId) => deliverToShop(shopId)
//    case LocalDelivery(address, timeframe) => deliverToAddress(address)
//    case GlobalDelivery(country, address) => mailTo(country, address)
//  }

}
