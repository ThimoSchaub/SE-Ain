package de.htwg.se.Minesweeper.controller

import de.htwg.se.Minesweeper.controller.GameStatus._
import de.htwg.se.Minesweeper.model.{Field}
import de.htwg.se.Minesweeper.util.{Observable, UndoManager}

class Controller(var field:Field) extends Observable{
  private val undoManager = new UndoManager
  var gameStatus: GameStatus = IDLE
  def createRandomField(xSize: Int, ySize: Int, mines: Int):Unit = {
    field = new Field(xSize, ySize, mines)
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

  def solve: Unit = {
    undoManager.doStep(new SolveCommand(this))
    notifyObservers
  }

  def undo: Unit = {
    undoManager.undoStep
    notifyObservers
  }

  def redo: Unit = {
    undoManager.redoStep
    notifyObservers
  }
}
