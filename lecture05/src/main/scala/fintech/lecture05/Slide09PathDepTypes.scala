package fintech.lecture05

object Slide09Example1 {

  case class Card(color: String, value: String)

  case class Player(name: String)

  trait Game {

    def getPlayers: List[Player]

    def getCards: List[Card]

    def playNewCard(player: Player, newCard: Card): Unit
  }
}

object Slide09Example2 {
  import Slide09Example1._

  val game1: Game = ???
  val game2: Game = ???
  game1.playNewCard(game2.getPlayers.head, game2.getCards.head)

}

object Slide09Example3 extends App {

  class Game {

    case class Card(color: String, value: String)

    case class Player(name: String)

    def getPlayers: List[Player] = List(Player("Alice"), Player("Bob"))

    def getCards: List[Card] = List(Card("red", "2"))

    def playNewCard(player: Player, newCard: Card): Unit = {}
  }

  val game1 = new Game
  val game2 = new Game
  println("Game1 players", game1.getPlayers) // returns List[game1.Card]
  println("Game2 players", game2.getPlayers) // returns List[game2.Card]

  game1.playNewCard(game1.getPlayers.head, game1.getCards.head)

  // fails!
  // game1.playNewCard(game2.getPlayers.head, game2.getCards.head)
}

object Slide09Example4 {
  import Slide09Example3._

  def processAnyPlayer(p: Game#Player): Unit = {}

  processAnyPlayer(game1.getPlayers.head)
  processAnyPlayer(game2.getPlayers.head)
}

object Slide09Example5 {
  import Slide09Example3._

  val game1 = new Game
  val player = new game1.Player("Alice")
}
