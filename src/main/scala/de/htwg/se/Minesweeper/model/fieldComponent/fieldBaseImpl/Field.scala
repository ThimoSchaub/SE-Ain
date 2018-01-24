package de.htwg.se.Minesweeper.model.fieldComponent.fieldBaseImpl


import de.htwg.se.Minesweeper.model.fieldComponent.{CellInterface, FieldInterface}
import play.api.libs.json.{JsNumber, JsValue, Json, Writes}
import com.google.inject.Inject
import com.google.inject.name.Named



case class Field @Inject()(@Named("DefaultSize")size:Int,@Named("DefaultSize")sizey:Int,@Named("DefaultMine")mines:Int) extends FieldInterface {
  var checkMine: Boolean = false
  var flags = 0
  val fieldSizeX: Int = size
  val fieldSizeY: Int = sizey
  var mine: Int = mines
  var visibleCells = 0
  val field: Array[Array[Cell]] = Array.ofDim[Cell](fieldSizeX, fieldSizeY)
  for (
    row <- 0 until fieldSizeX;
    col <- 0 until fieldSizeY
  ) {
    field(row)(col) = new Cell()
  }

  def getFieldSizeX: Int = fieldSizeX

  def getFieldSizeY: Int = fieldSizeY

  def getMines: Int = mine

  def getCell(x: Int, y: Int): Cell = {
    if (x < 0 || x >= fieldSizeX || y < 0 || y >= fieldSizeY) {
      return new Cell()
    }
    field(x)(y)
  }

  def setCell(x: Int, y: Int, state: Int): Unit =  field(x)(y).setState(state)


  def performAction(row: Int, col: Int, action: Int, manually: Boolean): Field = {
    action match {
      case 3 => {
        field(row)(col).toggleFlag()
        if (field(row)(col).getFlag()) {
          flags += 1
        } else {
          flags -= 1
        }
      }

      case 1 => {
        if (manually && getCell(row, col).isVisible && getRemainingFlags(row, col) == 0) for (
          x <- row - 1 until row + 2;
          y <- col - 1 until col + 2
        ) {
          if (!getCell(x, y).getFlag() && !getCell(x, y).isVisible) performAction(x, y, 1, false)
        }
        if (visibleCells == 0) {
          setMinesState(row, col)
          getCell(row, col).check()
          visibleCells += 1
          performAction(row, col, 1, false)
        } else if (getCell(row, col).getState() == 0 && (!getCell(row, col).isVisible)) {
          getCell(row, col).check()
          visibleCells += 1
          for (
            x <- row - 1 until row + 2;
            y <- col - 1 until col + 2
          ) {
            if (0 <= x && 0 <= y && y <= fieldSizeY - 1 && x <= fieldSizeX - 1) performAction(x, y, 1, false)
          }
        } else if (getCell(row, col).getState() > -1){
          field(row)(col).check()
          if (field(row)(col).check()) {
            allVisible()
            checkMine = true
          }
        }
      }

      case 0 => {
        field(row)(col).undoCheck()
        visibleCells -= 1
      }

      case _ =>
    }
    this
  }

  def getRemainingFlags(row: Int, col: Int): Int = {
    var remainingFlags = field(row)(col).getState()
    for (
      x <- row - 1 until row + 2;
      y <- col - 1 until col + 2
    ) {
      if (0 <= x && 0 <= y && y <= fieldSizeY - 1 && x <= fieldSizeX - 1) {
        if (field(x)(y).getFlag()) {
          remainingFlags -= 1
        }
      }
    }
    remainingFlags
  }

  def checkSolved: Boolean = {
    for (
      row <- 0 until fieldSizeX;
      col <- 0 until fieldSizeY
    ) {
      if (!field(row)(col).getVisibility() // cell invisible
        && field(row)(col).getState() != 9) // no mine
      {
        return false
      }
    }
    true
  }

  def allVisible(): Unit = {
    for (
      row <- 0 until fieldSizeX;
      col <- 0 until fieldSizeY
    ) {
      field(row)(col).setVisibility(true)
    }
  }
  def allunVisible(): Unit = {
    for (
      row <- 0 until fieldSizeX;
      col <- 0 until fieldSizeY
    ) {
      field(row)(col).setVisibility(false)
    }
  }

  def getRestMine: Int = mine - flags
  def setNew:Field={
    allunVisible()
    for (
      row <- 0 until fieldSizeX;
      col <- 0 until fieldSizeY
    ) {
      field(row)(col).setFlag(false)
      field(row)(col).setState(-1)
    }
    setMinesState(0,0)
    checkMine = false
    flags = 0
    return this
  }
  override def toString: String = {

    val lineSeparator = "+---" * fieldSizeY + "+\n"
    val line = ("|" + "to replace") * fieldSizeY + "|\n"
    var box = "\n" + (lineSeparator + line) * fieldSizeX + lineSeparator
    for {
      row <- 0 until fieldSizeX
      col <- 0 until fieldSizeY
    } {
      if (field(row)(col).isVisible && field(row)(col).getState() == 9 || field(row)(col).flag) {
        box = box.replaceFirst("to replace", field(row)(col).toString() + " ")
      }
      else {
        box = box.replaceFirst("to replace", " " + field(row)(col).toString() + " ")
      }
    }
    box + "Remaining mines: " + getRestMine
  }
  def set(row: Int, col: Int, isVisible: Boolean, state: Int, flag: Boolean): Field = {
    var cell = getCell(row, col)
    cell.setVisibility(isVisible)
    cell.setState(state)
    cell.setFlag(flag)
    this
  }
  def setMinesState(row: Int, col: Int): Unit = {
    val rand = scala.util.Random
    var mines_set = 0
    val minestate = 9
    while (
      mines_set < mine
    ) {
      val x = rand.nextInt(fieldSizeX)
      val y = rand.nextInt(fieldSizeY)
      val cell = getCell(x, y)
      if (cell.getState() != minestate && (!(x == row && y == col))) {
        cell.setState(minestate)
        mines_set += 1
      }
    }
    for (
      row <- 0 until fieldSizeX;
      col <- 0 until fieldSizeY
    ) {
      if (getCell(row, col).getState() != minestate) {
        var count = 0
        for (
          x <- row - 1 until row + 2;
          y <- col - 1 until col + 2
        ) {
          if (getCell(x, y).getState() == minestate) {
            count += 1
          }
        }
        getCell(row, col).setState(count)
      }
    }
  }


  implicit val cellWrites = new Writes[CellInterface] {
    def writes(cell: CellInterface) = Json.obj(
      "isVisible" -> cell.getVisibility(),
      "state" -> cell.getState(),
      "flag" -> cell.getFlag()
    )
  }

  def toJson:JsValue = {
    Json.obj(
      "field" -> Json.obj(
        "size" -> JsNumber(fieldSizeX),
        "cells" -> Json.toJson(
          for {row <- 0 until fieldSizeX;
               col <- 0 until fieldSizeX} yield {
            Json.obj(
              "row" -> row,
              "col" -> col,
              "cell" -> Json.toJson(getCell(row, col)))
          }
        )
      )
    )
  }

}