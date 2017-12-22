package de.htwg.se.Minesweeper

import de.htwg.se.Minesweeper.aview.gui.SwingGUI
import de.htwg.se.Minesweeper.aview.TUI
import de.htwg.se.Minesweeper.controller.Controller
import de.htwg.se.Minesweeper.model.Field

object Minesweeper {
  val controller = new Controller(new Field(10,10,10))
  val tui = new TUI(controller)
  val gui = new SwingGUI(controller)
  controller.notifyObservers
  def main(args: Array[String]): Unit = {
    var input = ""
    do {
      input = scala.io.StdIn.readLine()
      tui.processInputLine(input)
    }while(input != "quit")
  }
}
