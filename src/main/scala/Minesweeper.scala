package de.htwg.se.Minesweeper

import de.htwg.se.Minesweeper.aview.gui.SwingGUI
import de.htwg.se.Minesweeper.aview.TUI
import de.htwg.se.Minesweeper.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.Minesweeper.model.fieldComponent.fieldBaseImpl.Field

object Minesweeper {
  val controller = new Controller(new Field(11,11,15))
  val tui = new TUI(controller)
  val gui = new SwingGUI(controller)
  def main(args: Array[String]): Unit = {
    var input = ""
    do {
      input = scala.io.StdIn.readLine()
      tui.processInputLine(input)
      gui.redraw
      gui.resizable_=(true)
      gui.resizable_=(false)
    }while(input != "quit")
  }

}
