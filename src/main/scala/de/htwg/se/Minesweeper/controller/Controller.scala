package de.htwg.se.Minesweeper.controller

import de.htwg.se.Minesweeper.controller.GameStatus.{GameStatus, _}
import de.htwg.se.Minesweeper.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.Minesweeper.util.{Observable, UndoManager}

import scala.swing.Publisher
import scala.swing.event.Event

class Controller(var field:Field) extends Publisher {
  private val undoManager = new UndoManager
  var gameStatus: GameStatus = IDLE
  def createRandomField():Unit = {
    field = new Field(field.fieldsizex, field.fieldsizey, field.mine)
    gameStatus = NEW
    publish(new Cellchange)
  }


  def fieldToString: String = field.toString

  def set(row: Int, col: Int, action: Int): Unit = {
    undoManager.doStep(new SetCommand(row, col, action, this))
    if (field.checksolved){
      gameStatus = SOLVED
    }
    publish(new Cellchange)
  }

  def isSet(row: Int, col:Int):Boolean = field.getCell(row, col).getVisibility()

  def solve: Unit = {
    undoManager.doStep(new SolveCommand(this))
    gameStatus = SOLVED
    publish(new Cellchange)
  }
  def statusText:String = GameStatus.message(gameStatus)
  def undo: Unit = {
    undoManager.undoStep
    gameStatus = UNDO
    publish(new Cellchange)
  }

  def redo: Unit = {
    undoManager.redoStep
    gameStatus = REDO
    publish(new Cellchange)
  }
  def fieldSize:Int = field.fieldsizex*field.fieldsizey
  def blockSize:Int=Math.sqrt(fieldSize).toInt

  def cell(row: Int, col: Int) = field.getCell(row, col)
}
class Cellchange extends Event
