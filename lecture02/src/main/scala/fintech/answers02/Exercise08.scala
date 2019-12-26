package fintech.answers02

final case class PhoneNumber(first: Int, second: Int, third: Int)

object PhoneNumber extends App {

  val PhoneNumberExtractor = """^(.*),(.*),(\d+)-(\d+)-(\d+),(.*)$""".r

  def extractPhone(str: String): PhoneNumber =
    str match {
      case PhoneNumberExtractor(_, _, first, second, third, _) =>
        PhoneNumber(first.toInt, second.toInt, third.toInt)
      case _ =>
        throw new Exception("Unable to extract phone number")
    }

  println(extractPhone("Frank,123 Main,925-555-1943,95122"))
}
