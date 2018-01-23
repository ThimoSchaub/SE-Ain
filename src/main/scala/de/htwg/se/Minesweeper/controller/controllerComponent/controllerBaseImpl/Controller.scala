package de.htwg.se.Minesweeper.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.Minesweeper.controller.controllerComponent.{CellChange, ControllerInterface, FieldSizeChange, GameStatus}
import de.htwg.se.Minesweeper.controller.controllerComponent.GameStatus.{GameStatus, _}
import de.htwg.se.Minesweeper.model.fieldComponent.FieldInterface
import de.htwg.se.Minesweeper.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.Minesweeper.util.UndoManager

import scala.swing.Publisher
import scala.swing.event.Event

class Controller(var field:Field) extends ControllerInterface {

  private val undoManager = new UndoManager
  var gameStatus: GameStatus = IDLE

  def createRandomField():Unit = {
    field = new Field(fieldsizex, fieldsizey, field.getMines)
    field.visibleCells = 0
    gameStatus = NEW
    publish(new CellChange)
  }

  def fieldToString: String = field.toString

  def set(row: Int, col: Int, action: Int): Unit = {
    if(gameStatus != LOST && gameStatus != SOLVED){
      undoManager.doStep(new SetCommand(row, col, action, this))
      if (field.checksolved){
        gameStatus = SOLVED
      }
      if (field.checkmine){
        gameStatus = LOST
      }
      publish(new CellChange)
    }
  }

  def isSet(row: Int, col:Int):Boolean = field.getCell(row, col).getVisibility()

  def solve: Unit = {
    if(field.getCell(0,0).getState() == -1) {
      field.set_Mines_state(0,0)
    }
    undoManager.doStep(new SolveCommand(this))
    gameStatus = SOLVED
    publish(new CellChange)
  }
  def statusText:String = GameStatus.message(gameStatus)
  def undo: Unit = {
    undoManager.undoStep
    gameStatus = UNDO
    publish(new CellChange)
  }

  def redo: Unit = {
    undoManager.redoStep
    gameStatus = REDO
    publish(new CellChange)
  }
  def getFieldsize:Int = field.getFieldsizex*field.getFieldsizey
  def blockSize:Int=Math.sqrt(getFieldsize).toInt

  def cell(row: Int, col: Int) = field.getCell(row, col)

  override def fieldsizex = field.getFieldsizex

  override def fieldsizey = field.getFieldsizey

  override def getRest = field.getRestmine

  override def resize(size: Int, mines: Int): Unit = {
    field = new Field(size, size, mines)
    field.visibleCells = 0
    gameStatus = NEW
    publish(new FieldSizeChange(size))
  }
}

