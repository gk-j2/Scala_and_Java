package fintech.exercises02

// case class всегда должен быть final
final case class PhoneNumber(first: Int, second: Int, third: Int)

object Task01ParsePhone extends App {

  /*
    используя регулярное выражение напишите функцию
    (но можете использовать и split(""), которая выдергивает из строки вида
    Frank,123 Main,925-555-1943,95122 (имя,адрес,телефон,индекс).
    если строка не правильного формата - выбросите эксепшен.
    Обдумайте, почему даже если мы выкидываем эксепшен
    результирующий тип функции - PhoneNumber
  */
  def extractPhone(str: String): PhoneNumber = ???
}

object RegexExmpls extends App {
  import scala.util.matching.Regex
  val numberPattern: Regex = """[0-9]+""".r

  numberPattern.findFirstMatchIn("awesom123sword") match {
    case Some(x) => println(s"found digits $x")
    case None => println("not found")
  }

  val res = numberPattern.findFirstMatchIn("awesom123sword")
  if (res.isDefined) {
    println(res.get)
  }

  val keyValPattern: Regex = "([0-9a-zA-Z-#() ]+): ([0-9a-zA-Z-#() ]+)".r

  val input: String =
    """background-color: #A03300;
      |background-image: url(img/header100.png);
      |background-position: top center;
      |background-repeat: repeat-x;
      |background-size: 2160px 108px;
      |margin: 0;
      |height: 108px;
      |width: 100%;""".stripMargin

  for (patternMatch <- keyValPattern.findAllMatchIn(input))
    println(s"key: ${patternMatch.group(1)} value: ${patternMatch.group(2)}")
}
