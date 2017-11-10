package de.htwg.se.Minesweeper
import de.htwg.se.Minesweeper.controller.Controller
import de.htwg.se.Minesweeper.aview.Tui
import de.htwg.se.Minesweeper.model.{Cell, Field, Player}

object Minesweeper {
  val controller = new Controller(new Field(0,0,0))
  val tui = new Tui(controller)
  controller.notifyObservers
  def main(args: Array[String]): Unit = {
    var input = ""
    do {
      input = io.StdIn.readLine()
      tui.processInputLine(input)
    }while(input != "q")
  }
}
