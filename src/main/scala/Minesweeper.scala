package de.htwg.se.Minesweeper
import de.htwg.se.Minesweeper.aview.TUI
import de.htwg.se.Minesweeper.controller.Controller
import de.htwg.se.Minesweeper.model.Field

object Minesweeper {
  val controller = new Controller(new Field(0,0,0))
  val tui = new TUI(controller)
  controller.notifyObservers
  def main(args: Array[String]): Unit = {
    var input = ""
    do {
      input = io.StdIn.readLine()
      tui.processInputLine(input)
    }while(input != "quit")
  }
}
