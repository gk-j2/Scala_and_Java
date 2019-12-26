package fintech.assignment08.reader

import java.time.LocalDate

// В файле RBRes небольшое API для работы с базами данных на основе JDBC.
// С использованием этого API написано приложение PeopleApp, которое взаимодействует с базой данных.
//
// К сожалению, когда я запускаю приложение, я вижу, что оно коннектится к базе много раз.
//
// Добавьте в DBRes методы map и flatMap и перепишите код, используя for comprehension так, чтобы
// подключение к базе данных происходило только один раз.
//
// Указания:
// 1. В файле DBRes вы не можете изменять методы. Только добавлять реализации для отсутствующих методов.
// 2. В файле PeopleModule вы должны определить реализацию для метода setup, который должен быть
//    эквивалентен существующему методу setup(String).
// 3. В файле PeopleApp вы можете переписать реализацию метода main(String)
object PeopleApp extends PeopleModule {

  def getOldPerson(uri: String): Person =
    DBRes.select("SELECT * FROM people WHERE birthday < ?", List(LocalDate.of(1979, 2, 20)))(readPerson).execute(uri).head

  // Метод getOldPerson возвращает Person. Хотя это не будет работать, если таблица пустая.
  // В новой реализации вы должны возвращать Option[Person].
  lazy val oldPersonDBRes: DBRes[Option[Person]] = ???

  def clonePerson(uri: String, person: Person): Person = {
    val clone = person.copy(birthday = LocalDate.now())
    storePerson(clone).execute(uri)
    clone
  }

  def clonePersonDBRes(person: Person): DBRes[Person] = ???

  def main(uri: String): Unit = {
    setup(uri)

    val old = getOldPerson(uri)
    val clone = clonePerson(uri, old)

    println(clone)
  }

  def main(args: Array[String]): Unit = main("jdbc:h2:./dbres")
}
