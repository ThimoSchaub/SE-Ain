package de.htwg.se.Minesweeper.controller

import de.htwg.se.Minesweeper.model.{Field, Cell}
import de.htwg.se.Minesweeper.util.Observable

class Controller(var field:Field) extends Observable{
  def createRandomField(xSize: Int, ySize: Int):Unit = {
    field = new Field(xSize, ySize, field.mines)
    notifyObservers
  }

}
