package fintech.lecture10

import io.circe.Decoder
import io.circe.Encoder
import io.circe.Json
import io.circe.HCursor
import io.circe.ParsingFailure
import io.circe.parser

/**
  * Comment
  */
object Slide01Example1 extends App {

  val rawJson: String = """{"a": 1, "someString": "value", "array": [1, 2, 3]}"""

  val result1: Either[ParsingFailure, Json] = parser.parse(rawJson)

  val result2: Either[ParsingFailure, Json] = parser.parse("invalid-json")

  println(result1)

  println(result2)
}

object Slide01Example2 extends App {

  val rawJson = """{"name": "Alice", "id": 5, "groups": ["root", "sudo", "audio"]}"""

  val json = parser.parse(rawJson).getOrElse(Json.Null)
  val cursor: HCursor = json.hcursor
  
  println(cursor.downField("name").as[String])
  println(cursor.downField("id").as[Int])
  println(cursor.downField("groups").downN(2).as[String])
}

object Slide01Example3 extends App {

  case class User(name: String, id: Int, groups: List[String])

  val rawJson = """{"name": "Alice", "id": 20, "groups": ["root", "sudo", "audio"]}"""

  implicit val userDecoder = new Decoder[User] {
    override def apply(cursor: HCursor) =
      for {
        name <- cursor.downField("name").as[String]
        id <- cursor.downField("id").as[Int]
        groups <- cursor.downField("groups").as[List[String]]
      } yield User(name, id, groups)
  }

  val user = parser.parse(rawJson).getOrElse(Json.Null).as[User]

  println(user)
}

object Slide01Example4 {

  case class SomeClass(field1: String, field2: String, is: java.io.InputStream)

  val json: Json = ???

  // json.as[SomeClass]
}

object Slide01Example5 {
  import io.circe.generic.auto._

  case class User(name: String, id: Int, groups: List[String])

  val rawJson = """{"name": "Alice", "id": 5, "groups": ["root", "sudo", "audio"]}"""

  val user = parser.parse(rawJson).getOrElse(Json.Null).as[User]
}

object Slide01Example7 {
  import io.circe.generic.semiauto._

  case class Foo(a: Int, b: String, c: Boolean)
  case class Bar(foo1: Foo, foo2: Foo)

  implicit val fooDecoder: Decoder[Foo] = deriveDecoder[Foo]
  implicit val barDecoder: Decoder[Bar] = deriveDecoder[Bar]

  val json: Json = ???

  json.as[Foo]
  json.as[Bar]
}

object Slide01Example8 extends App {
  import io.circe.syntax._

  val intsJson: Json = List(1, 2, 3).asJson
  println(intsJson.noSpaces)
  println(intsJson.spaces2)
}

object Slide01Example9 extends App {

  import io.circe.parser
  import io.circe.generic.semiauto._
  import io.circe.syntax._

  case class Group(id: Int, name: String)
  case class User(id: Int, name: String, groups: List[Group])

  implicit val groupDecoder: Decoder[Group] = deriveDecoder[Group]
  implicit val groupEncoder: Encoder[Group] = deriveEncoder[Group]

  implicit val userDecoder: Decoder[User] = deriveDecoder[User]
  implicit val userEncoder: Encoder[User] = deriveEncoder[User]

  val alice = User(1, "Alice", List(Group(0, "root"), Group(1, "sudo")))

  val json = alice.asJson.noSpaces
  println(json)

  val restoredAlice = parser.parse(json).getOrElse(Json.Null).as[User]
  println(Right(alice) == restoredAlice)

}
