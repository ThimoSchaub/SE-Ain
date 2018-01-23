package de.htwg.se.Minesweeper.model.fieldComponent


trait FieldInterface {
  def getFieldsizex : Int
  def getFieldsizey: Int
  def getMines: Int
  def getCell(x: Int, y: Int): CellInterface
  //def setCell(x: Int, y: Int, state: Int): Unit
  def performAction(row: Int, col: Int, action: Int, manually: Boolean):FieldInterface
  def checksolved: Boolean
  //def allVisible: Unit
  //def set_Mines_state(): Unit
}
trait CellInterface{
  def getState(): Int
  def getVisibility():Boolean
  def getFlag(): Boolean
  def check(): Boolean
}
