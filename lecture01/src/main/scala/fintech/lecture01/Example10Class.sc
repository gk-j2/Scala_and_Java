trait Printing {
  def print(): Unit
}

class Vehicle(description: String) extends Printing {
  println("constructing...")
  val name = description + " vehicle"
  def print(): Unit = println(name)
}

class Car extends Vehicle("heavy")

val car = new Car

car.print()

object Vehicle {
  def produce(): Vehicle =
    new Vehicle("from factory")
}

Vehicle.produce().print()

object CarSingle extends Vehicle("heavy single")
CarSingle.print()
