package de.htwg.se.Minesweeper.model.fieldComponent

import de.htwg.se.Minesweeper.model.fieldComponent.fieldBaseImpl.Field


trait FieldInterface {
  def getFieldsizex : Int
  def getFieldsizey: Int
  def getMines: Int
  def getCell(x: Int, y: Int): CellInterface
  def setCell(x: Int, y: Int, state: Int): Unit
  def performAction(row: Int, col: Int, action: Int):Field
  def checksolved: Boolean
  def allVisible: Unit
  override def toString: String
  def set_Mines_state(): Unit
}
trait CellInterface{
  def getState(): Int
  def setState(int: Int): Unit
  def getVisibility():Boolean
  def setVisibility(visible:Boolean)
  def getFlag(): Boolean
  def setFlag(toFlag: Boolean): Unit
  def toggleFlag(): Unit
  def check(): Boolean
  def undocheck(): Unit
  override def toString(): String
}
