package de.htwg.se.Minesweeper.model.fieldComponent.fieldMockImpl
import de.htwg.se.Minesweeper.model.fieldComponent.{CellInterface, FieldInterface, fieldBaseImpl}
import de.htwg.se.Minesweeper.model.fieldComponent.fieldBaseImpl.Field

class Field(var x:Int,var y :Int,var mine :Int) extends FieldInterface{
  override def getFieldSizeX: Int = x

  override def getFieldSizeY: Int = y

  override def getMines: Int = mine

  override def getCell(x: Int, y: Int): CellInterface = RandomCell

  override def performAction(row: Int, col: Int, action: Int, manually: Boolean): FieldInterface = this

  override def checkSolved: Boolean = false

}
object RandomCell extends CellInterface{
  override def getState(): Int = 0

  override def getVisibility(): Boolean = false

  override def getFlag(): Boolean = false

  override def check(): Boolean = false
}
