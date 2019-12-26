package fintech.lecture12

import scala.io.StdIn.readLine
import scala.util.Try

object App1 {

  def parseInt(s: String): Option[Int] =
    Try(s.toInt).toOption

  def main(args: Array[String]): Unit = {
    println("What is your name?")

    val name = readLine()

    println(s"Hello, $name, welcome to the game!")

    var exec = true

    while (exec) {
      val num = scala.util.Random.nextInt(5) + 1

      println(s"Dear $name, please guess a number from 1 to 5:")

      parseInt(readLine()) match {
        case None =>
          println("You did not enter a number")
        case Some(guess) =>
          if (guess == num) {
            println(s"You guessed right, $name!")
          } else {
            println(s"You guessed wrong, $name! The number was: $num")
          }
      }

      var cont = true
      while (cont) {
        cont = false
        println(s"Do you want to continue, $name?")

        readLine() match {
          case "y" => exec = true
          case "n" => exec = false
          case _ => cont = true
        }
      }
    }
  }
}
