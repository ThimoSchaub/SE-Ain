package de.htwg.se.Minesweeper.model.fieldComponent

import play.api.libs.json.JsValue


trait FieldInterface {
  def getFieldSizeX : Int
  def getFieldSizeY: Int
  def getMines: Int
  def getCell(x: Int, y: Int): CellInterface
  def checkMine:Boolean
  var visibleCells:Int
  //def setCell(x: Int, y: Int, state: Int): Unit
  def performAction(row: Int, col: Int, action: Int, manually: Boolean):FieldInterface
  def checkSolved: Boolean
  def allVisible: Unit
  def setMinesState(row:Int,col:Int): Unit
  def getRestMine:Int
  def set(row: Int, col: Int, isVisible: Boolean, state: Int, flag: Boolean): FieldInterface
  def toJson: JsValue
}
trait CellInterface{
  def getState(): Int
  def getVisibility():Boolean
  def getFlag(): Boolean
  def check(): Boolean
}
