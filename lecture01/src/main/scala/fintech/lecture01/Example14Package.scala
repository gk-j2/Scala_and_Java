package fintech.lecture01

package foo {
  case class Data(value: Int)
}

object Example14Package {
  import foo._

  Data(42)
}
