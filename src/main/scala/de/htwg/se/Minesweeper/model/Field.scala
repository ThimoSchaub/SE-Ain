package de.htwg.se.Minesweeper.model

import de.htwg.se.Minesweeper.model.Cell

case class Field(var x:Int,var y:Int,var mines:Int) {
  val fieldsizex = x
  val fieldsizey = y
  var mine = mines
  val field = Array.ofDim[Cell](x, y)
  for (
    row <- 0 until fieldsizex;
    col <- 0 until fieldsizey
  ) {
    field(row)(col) = new Cell()
  }
  set_Mines_state()

  def getCell(x: Int, y: Int): Cell = {
    if (x < 0 || x >= fieldsizex || y < 0 || y >= fieldsizey) {
      return new Cell()
    }
    field(x)(y)
  }

  def setCell(x: Int, y: Int, state: Int): Unit = {
      field(x)(y).setState(state)
  }


  def performAction(row: Int, col: Int, action: Int):Field= {
    action match {
      case 3 => field(row)(col).toggleFlag()
      case 1 => field(row)(col).check()
      case _ =>
    }
    return this
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
    } {
      if (field(row)(col).isVisible && field(row)(col).getState() == 9 || field(row)(col).flag) {
        (box = box.replaceFirst("toreplace", field(row)(col).toString() + " "))
      }
      else {
        (box = box.replaceFirst("toreplace", " " + field(row)(col).toString() + " "))
      }
    }
    box
  }

  def set_Mines_state(): Unit = {
    var rand = scala.util.Random
    var mines_set = 0;
    while (
      mines_set < mine
    ) {
      var cell = getCell(rand.nextInt(fieldsizex), rand.nextInt(fieldsizey))
      if(cell.getState()!=9){
        cell.setState(9)
        mines_set += 1
      }
    }
    for (
      row <- 0 until fieldsizex;
      col <- 0 until fieldsizey
    ) {
      if (getCell(row, col).getState() != 9) {
        var count = 0;
        for (
          x <- row - 1 until row + 2;
          y <- col - 1 until col + 2
        ) {
          if (getCell(x, y).getState() == 9) {
            count += 1
          }
        }
        getCell(row, col).setState(count)
      }
    }
  }
}