trait Flying {
  def wings: String
  def fly(): String =
    "Flapping my " + wings
}

trait Bird {
  def wings: String =
    "feathered wings"
}

class Pigeon extends Bird with Flying
class Penguin extends Bird

new Pigeon().fly()
//can't fly
//new Penguin().fly()

class DragonFly extends Flying {
  def wings: String =
    "glassy wings"
}

object Icarus extends Flying {
  def wings: String =
    "wax wings"
}

Icarus.fly()
