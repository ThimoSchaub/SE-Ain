package de.htwg.se.Minesweeper.controller

import de.htwg.se.Minesweeper.controller.GameStatus.{GameStatus, _}
import de.htwg.se.Minesweeper.model.Field
import de.htwg.se.Minesweeper.util.{Observable, UndoManager}

class Controller(var field:Field) extends Observable {
  private val undoManager = new UndoManager
  var gameStatus: GameStatus = IDLE
  def createRandomField():Unit = {
    field = new Field(10, 10, 10)
    gameStatus = NEW
    notifyObservers
  }

  def performAction(row: Int, col: Int, action: Int):Unit = {
    undoManager.doStep(new SetCommand(row, col, action, this))
    field.performAction(row, col, action)
    notifyObservers
  }

  def fieldToString: String = field.toString 

  def set(row: Int, col: Int, action: Int): Unit = {
    undoManager.doStep(new SetCommand(row, col, action, this))
    notifyObservers
  }

  def isSet(row: Int, col:Int):Boolean = field.getCell(row, col).getVisibility()

  def solve: Unit = {
    undoManager.doStep(new SolveCommand(this))
    gameStatus = SOLVED
    notifyObservers
  }
  def statusText:String = GameStatus.message(gameStatus)
  def undo: Unit = {
    undoManager.undoStep
    gameStatus = UNDO
    notifyObservers
  }

  def redo: Unit = {
    undoManager.redoStep
    gameStatus = REDO
    notifyObservers
  }
  def fieldSize:Int = field.fieldsizex*field.fieldsizey
  def blockSize:Int=Math.sqrt(fieldSize).toInt

  def cell(row: Int, col: Int) = field.getCell(row, col)
}
