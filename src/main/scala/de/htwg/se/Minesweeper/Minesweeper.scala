package de.htwg.se.Minesweeper

import com.google.inject.Guice
import de.htwg.se.Minesweeper.aview.TUI
import de.htwg.se.Minesweeper.aview.gui.SwingGUI
import de.htwg.se.Minesweeper.controller.controllerComponent.ControllerInterface


object Minesweeper {
  val injector = Guice.createInjector(new MinesweeperModule)
  val controller = injector.getInstance(classOf[ControllerInterface])
  val tui = new TUI(controller)
  val gui = new SwingGUI(controller)
  def main(args: Array[String]): Unit = {
    var input = ""
    do {
      input = scala.io.StdIn.readLine()
      tui.processInputLine(input)
      gui.resizable_=(true)
      gui.resizable_=(false)
      gui.redraw

    }while(input != "quit")
  }

}
