package de.htwg.se.Minesweeper.model

import de.htwg.se.Minesweeper.model.Cell

case class Field(x:Int,y:Int,mines:Int) {

  val field = Array.ofDim[Cell](x,y)

  def getCell(x:Int,y:Int):Cell = field(x)(y)
  def setCell(x:Int,y:Int,cell:Cell): Unit ={
    field(x)(y) = cell
  }

  override def toString: String = {

    val lineseparator = ("+---") * y + "+\n"
    val line = ("|" + ("   ")) * y + "|\n"
    var box = "\n" + (lineseparator + (line)) * x + lineseparator
    for {
      row <- 0 until x-1
      col <- 0 until y-1
    } (box = box.replaceAll("   ", " " + field(row)(col).toString + " "))
    box
  }
}
