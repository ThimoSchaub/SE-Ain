package de.htwg.se.Minesweeper.aview

import de.htwg.se.Minesweeper.controller.Controller
import de.htwg.se.Minesweeper.util.Observer

class Tui(controller: Controller) extends Observer {

  controller.add(this)
  val sizex, sizey = 10
  val mines = 100

  def processInputLine(input: String): Unit = {
    input match {
      case "quit" =>
      case "new" => controller.createRandomField(sizex, sizey, mines)
      case _ => input.toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
        case row :: column :: action :: Nil => controller.performAction(row, column, action)
        case _ =>
      }
    }
  }

  override def update: Unit = println(controller.fieldToString)
}
