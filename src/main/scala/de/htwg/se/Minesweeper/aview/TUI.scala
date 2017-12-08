package de.htwg.se.Minesweeper.aview

import de.htwg.se.Minesweeper.controller.Controller
import de.htwg.se.Minesweeper.util.Observer

class TUI(controller: Controller) extends Observer {

  controller.add(this)
  val sizex, sizey = 5
  val mines = 2

  def processInputLine(input: String): Unit = {
    input match {
      case "quit" =>
      case "z" => controller.undo
      case "y" => controller.redo
      case "s" => controller.solve
      case "new" => controller.createRandomField(sizex, sizey, mines)
      case _ => input.toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
        case row :: column :: action :: Nil => controller.set(row, column, action)
        case _ =>
      }
    }
  }

  override def update: Unit = println(controller.fieldToString)
}
