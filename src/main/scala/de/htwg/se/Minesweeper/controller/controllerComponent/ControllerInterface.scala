package de.htwg.se.Minesweeper.controller.controllerComponent
import de.htwg.se.Minesweeper.controller.controllerComponent.GameStatus.GameStatus
import de.htwg.se.Minesweeper.model.fieldComponent.{CellInterface, FieldInterface}

import scala.swing.Publisher
trait ControllerInterface extends Publisher{
  def getRest:Int
  def fieldsizex:Int
  def fieldsizey:Int
  def createRandomField():Unit
  def undo:Unit
  def redo:Unit
  def solve: Unit
  def fieldToString:String
  var gameStatus:GameStatus
  def statusText:String
  def cell(row:Int, col:Int):CellInterface
  def set(row:Int, col:Int, value:Int):Unit
  def blockSize:Int
  def resize(size:Int,mines:Int):Unit
  def save: Unit
  def load: Unit
}

import scala.swing.event.Event

class CellChange extends Event
case class FieldSizeChange(newSize:Int) extends Event
