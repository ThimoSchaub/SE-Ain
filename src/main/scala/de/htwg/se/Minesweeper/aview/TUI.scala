package de.htwg.se.Minesweeper.aview

import com.typesafe.scalalogging.{LazyLogging, Logger}
import de.htwg.se.Minesweeper.controller.controllerComponent.{CellChange, ControllerInterface, FieldSizeChange, GameStatus}

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
      case "easy" =>controller.resize(5)
      case "medium"=> controller.resize(10)
      case "heavy"=>controller.resize(15)
      case "help"=>println("not implement yet")
      case "save"=>controller.save
      case "load"=>controller.load
      case _ => input.toList.filter(c => c != ' ').map(c => c.toString.toInt) match {
        case row :: column :: action :: Nil => controller.set(row, column, action)
        case _ =>
      }
    }
  }

  reactions+={
    case sizeevent:FieldSizeChange =>
      println(controller.fieldToString)
      println(controller.statusText)
    case cellevent:CellChange =>
      println(controller.fieldToString)
      println(controller.statusText)
  }
}
