package fintech.lecture03.examples

object Example12ClassSelf extends App {

  class Widget {
    def show(): Unit = println(s"show ${this}")
    def hide(): Unit = println(s"hide ${this}")
  }

  class Button extends Widget {
    def onClick(): Unit = ???
  }

  class Window extends Widget {

    val closeButton = new Button {
      override def onClick(): Unit = this.hide()

      override def toString: String = "closeButton"
    }

    override def toString: String = "window"
  }

  val window = new Window
  window.closeButton.onClick()

  println("-"*42)

  class GoodWindow extends Widget { self =>

    val closeButton = new Button {
      override def onClick(): Unit = self.hide()

      override def toString: String = "closeButton"
    }

    override def toString: String = "goodWindow"
  }

  val goodWindow = new GoodWindow
  goodWindow.closeButton.onClick()
}
