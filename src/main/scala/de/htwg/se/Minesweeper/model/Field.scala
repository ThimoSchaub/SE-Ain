package de.htwg.se.Minesweeper.model

import de.htwg.se.Minesweeper.model.Cell

case class Field(x:Int,y:Int,mines:Int) {
  val fieldsizex = x;
  val fieldsizey = y;
  val field = Array.ofDim[Cell](x,y)

  def getCell(x:Int,y:Int):Cell = field(x)(y)
  def setCell(x:Int,y:Int,cell:Cell): Unit ={
    field(x)(y) = cell
  }

  override def toString: String = {

    val lineseparator = ("+---") * y + "+\n"
    val line = ("|" + ("toreplace")) * y + "|\n"
    var box = "\n" + (lineseparator + (line)) * x + lineseparator
    for {
      row <- 0 until fieldsizex
      col <- 0 until fieldsizey
    } (box = box.replaceFirst("toreplace", " " + field(row)(col).toString() + " "))
    box
  }
}
