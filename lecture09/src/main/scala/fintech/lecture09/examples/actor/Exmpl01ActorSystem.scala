package fintech.lecture09.examples.actor

import akka.actor.ActorSystem

import scala.io.StdIn

object Exmpl01ActorSystem extends App {
  val system = ActorSystem("myactorz")

  println("das actor system started")

  system.terminate()
}
