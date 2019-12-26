package fintech.lecture03.examples

object Example19TraitsInterfaces extends App {
  trait KVInterface {
    def store(key: String, value: String): Unit
    def find(key: String): Option[String]
  }

  class Program(storage: KVInterface) {
    def doStuff(id: String): String = {
      val data = storage.find(id)
      val result = data.fold("Not Found")(s => "Data: " + s)

      result
    }
  }
}
