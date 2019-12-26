package fintech.lecture03.exercises.answers

object Exercise02 {

  // Используя mutable Map написать реализацию для интерфейса KVInterface. Скрыть детали реализации.
  // Добавить логирование вызовов (в консоль с помощью println). Вынести функции логирования в отдельный трейт Logging

  trait KVInterface {
    def store(key: String, value: String): Unit
    def find(key: String): Option[String]
  }

  trait Logger {
    def log(msg: String): Unit
  }

  class MutableMapKV(logger: Logger) extends KVInterface {
    import scala.collection.mutable

    val store = mutable.Map.empty[String, String]

    override def store(key: String, value: String): Unit = {
      logger.log(s"store $key $value")
      store.put(key, value)
    }

    override def find(key: String): Option[String] = {
      logger.log(s"get $key")
      store.get(key)
    }
  }
}
