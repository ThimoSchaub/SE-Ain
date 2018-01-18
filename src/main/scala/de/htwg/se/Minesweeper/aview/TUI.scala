package de.htwg.se.Minesweeper.aview

import de.htwg.se.Minesweeper.controller.controllerComponent.{CellChange, ControllerInterface, FieldSizeChange}

import scala.swing.Reactor

class TUI(controller: ControllerInterface)extends Reactor{
  println(controller.fieldToString)

  listenTo(controller)
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
  reactions+={
    case sizeevent:FieldSizeChange=>
      println(controller.fieldToString)
      println(controller.statusText)
    case cellevent:CellChange=>{
      println(controller.fieldToString)
      println(controller.statusText)
    }
  }
}
