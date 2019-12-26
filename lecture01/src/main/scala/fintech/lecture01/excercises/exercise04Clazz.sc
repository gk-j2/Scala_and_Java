// 1. Создайте объект типа Hippo с массой 200
// Вызовите метод printWeight
// Попробуйте вывести массу напрямую. Получилось?
class Animal

class Hippo(weight: Double) extends Animal {
  def printWeight(): Unit = println(weight + "KG")
}

val hippo: Hippo = ???
hippo.printWeight()
// println(hippo.weight)

// 2. Создайте объект типа Turtle с возрастом 2399 месяцев
// Выведите на экран возрас в годах
// Выведите на экран возаст в месяцах
// Почему теперь есть доступ к параметру конструктора?

class Turtle(val ageInMonth: Int) extends Animal {
  val age = ageInMonth / 12
}

