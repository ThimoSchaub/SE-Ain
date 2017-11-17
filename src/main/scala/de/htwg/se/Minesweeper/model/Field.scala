package de.htwg.se.Minesweeper.model

import de.htwg.se.Minesweeper.model.Cell

case class Field(var x:Int,var y:Int,var mines:Int) {
  val fieldsizex = x;
  val fieldsizey = y;
  val field = Array.ofDim[Cell](x,y)
  for(
    row <- 0 until fieldsizex;
    col <- 0 until fieldsizey
  ){
    field(row)(col) = new Cell()
  }
  def getCell(x:Int,y:Int):Cell = field(x)(y)
  def setCell(x:Int,y:Int,cell:Cell): Unit ={
    field(x)(y) = cell
  }

  def performAction(row: Int, col: Int, action: Int) {
    action match {
      case 1 => field(row)(col).toggleFlag()
      case 2 => field(row)(col).check()
    }
  }

  def allVisible: Unit = {
    for (
      row <- 0 until fieldsizex;
      col <- 0 until fieldsizey
    ) {
      field(row)(col).setVisibility(true)
    }
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
