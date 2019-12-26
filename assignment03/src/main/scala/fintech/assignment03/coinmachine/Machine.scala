package fintech.assignment03.coinmachine

case class Machine(locked: Boolean, candies: Int, coins: Int)

object Machine {
  @scala.annotation.tailrec
  def run(machine: Machine, inputs: List[Input]): (Machine, List[Input]) = ???
}
