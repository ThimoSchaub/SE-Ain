package de.htwg.se.Minesweeper.controller

import de.htwg.se.Minesweeper.model.{Field, Cell}
import de.htwg.se.Minesweeper.util.Observable

class Controller(var field:Field) extends Observable{
  def createRandomField(xSize: Int, ySize: Int, mines: Int):Unit = {
    field = new Field(xSize, ySize, mines)
    field.set_Mines_state()
    notifyObservers
  }

  def performAction(row: Int, col: Int, action: Int):Unit = {
    field.performAction(row, col, action)
    notifyObservers
  }

  def fieldToString: String = field.toString

  def solve: Unit = {
    field.allVisible
    notifyObservers
  }
}
