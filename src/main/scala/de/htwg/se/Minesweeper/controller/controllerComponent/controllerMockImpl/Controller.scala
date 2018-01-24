package de.htwg.se.Minesweeper.controller.controllerComponent.controllerMockImpl

import de.htwg.se.Minesweeper.controller.controllerComponent.{ControllerInterface, GameStatus}
import de.htwg.se.Minesweeper.controller.controllerComponent.GameStatus._
import de.htwg.se.Minesweeper.model.fieldComponent.{CellInterface, FieldInterface}
import de.htwg.se.Minesweeper.model.fieldComponent.fieldMockImpl.Field
import de.htwg.se.Minesweeper.controller.controllerComponent.GameStatus.GameStatus

import scala.swing.event.Event
class Controller(var field:FieldInterface)extends ControllerInterface{
  field = new Field(1,1,1)
  override def createRandomField(): Unit = {}

  override def undo: Unit = {}

  override def redo: Unit ={}

  override def solve: Unit = {}

  override def fieldToString: String = field.toString

  override var gameStatus: GameStatus = IDLE

  override def statusText: String = GameStatus.message(gameStatus)

  override def cell(row: Int, col: Int): CellInterface = field.getCell(row,col)

  override def set(row: Int, col: Int, value: Int): Unit = {}

  override def blockSize: Int = 1

  override def fieldsizex = 1

  override def fieldsizey = 1

  override def getRest = 1

  override def resize(size: Int): Unit = {}
}

