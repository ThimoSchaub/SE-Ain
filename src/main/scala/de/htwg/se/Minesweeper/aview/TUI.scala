package de.htwg.se.Minesweeper.aview

import de.htwg.se.Minesweeper.controller.Controller
import de.htwg.se.Minesweeper.util.Observer

class TUI(controller: Controller) extends Observer {

  controller.add(this)

  def processInputLine(input: String): Unit = {

    input match {
      case "quit" => System.exit(0)
      case "z" => controller.undo
      case "y" => controller.redo
      case "s" => controller.solve
      case "new" => controller.createRandomField()
      case _ => input.toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
        case row :: column :: action :: Nil => controller.set(row, column, action)
        case _ =>
      }
    }
  }

  override def update: Unit = println(controller.fieldToString)
}
