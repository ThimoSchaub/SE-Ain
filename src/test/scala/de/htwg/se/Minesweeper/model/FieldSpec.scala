package de.htwg.se.Minesweeper.model

import de.htwg.se.Minesweeper.model.fieldComponent.fieldBaseImpl.{Cell, Field}
import org.scalatest.{Matchers, WordSpec}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import io.github.todokr.Emojipolation._
import play.api.libs.json.{JsNumber, Json}

@RunWith(classOf[JUnitRunner])
class FieldSpec extends WordSpec with Matchers {
  val MINE = 9
  "A Field is the playingfield  of Minesweeper" when {
    "small and without mines" should {
      val field = new Field(1,1,0)
      "have a fieldSizeX"  in {
        field.fieldSizeX should be(1)
      }
      "have a fieldSizeY"  in {
        field.fieldSizeY should be(1)
      }
      "have no mines"  in {
        field.mines should be(0)
      }
      "have a getFieldSizeX" in {
        field.getFieldSizeX should be(1)
      }
      "have a getFieldSizeY" in {
        field.getFieldSizeY should be(1)
      }
      "have a getMines" in {
        field.getMines should be(0)
      }
      "have a getCell" in {
        field.getCell(0,0).toString() should be(new Cell().toString())
        field.getCell(1,1).toString() should be(new Cell().toString())
      }
      "have a nice String representation" in {
        field.toString should be("\n+---+\n|   |\n+---+\nRemaining mines: 0")
      }
      "have an Action to toggle Flag" in {
        field.performAction(0,0,3,true)
        field.getCell(0,0).getFlag() should be(true)
        field.flags should be(1)
        field.performAction(0,0,3,true)
        field.getCell(0,0).getFlag() should be(false)
        field.flags should be(0)
      }
      "check and uncheck a Cell" in {
        field.performAction(0,0,1,true)
        field.getCell(0,0).getVisibility() should be(true)
        field.visibleCells should be(1)
        field.performAction(0,0,0,true)
        field.getCell(0,0).getVisibility() should be(false)
        field.visibleCells should be(0)
      }
      "check if field is solved" in {
        field.performAction(0,0,1,true)
        field.checkSolved should be(true)
        field.performAction(0,0,0,true)
        field.setCell(0,0,MINE)
        field.checkSolved should be(true)
        field.setCell(0,0,0)
        field.checkSolved should be(false)
      }
      "have a set method" in {
        val f = field.set(0,0,isVisible = true,0,flag = false)
        val cell = field.getCell(0,0)
        cell.getVisibility() should be(true)
        cell.getState() should be(0)
        cell.getFlag() should be(false)
        f.toString should be("\n+---+\n|" + " 0" + ""+" |\n+---+\nRemaining mines: 0")
        val f1 = field.set(0,0,isVisible = true,MINE,flag = false)
        val cell1 = field.getCell(0,0)
        cell1.getVisibility() should be(true)
        cell1.getState() should be(MINE)
        cell1.getFlag() should be(false)
        f1.toString should be("\n+---+\n|"+emoji":bomb:"+" |\n+---+\nRemaining mines: 0")
      }
    }
    "bigger and with a non random mine" should {
      val field = new Field(3,3,0)
      field.setCell(0,0,MINE)
      "check all other Cells if enough flags and check on visible" in {
        field.performAction(0,0,3,true)
        field.performAction(0,1,1,true)
        field.performAction(0,1,1,true)
        for (
          row <- 0 until field.fieldSizeX;
          col <- 0 until field.fieldSizeY
        ) {
          if (!(row == 0 && col == 0)) field.getCell(row, col).getVisibility() should be(true)
        }
      }
      "check all Cells surrounding a 0" in {
        field.performAction(2,2,1,true)
        field.performAction(2,2,1,true)
        for (
          row <- 0 until field.fieldSizeX;
          col <- 0 until field.fieldSizeY
        ) {
          field.getCell(row, col).getVisibility()
        }
      }
      "check a Cell with a mine" in {
        field.getCell(0,0).setFlag(false)
        field.performAction(0,0,1,true)
        field.checkMine should be(true)
      }
      "reset the field and set every Cell to default" in {
        field.setNew
        for (
        row <- 0 until field.fieldSizeX;
        col <- 0 until field.fieldSizeY
        ) {
          val cell = field.getCell(row, col)
          cell.getVisibility() should be(false)
          cell.getState() should be(-1)
          cell.getFlag() should be(false)
        }
      }
    }
  }

  "A Field" when {
    "setCell" should {
      val field = new Field(1,1,0)
      val testState = 9
      field.setCell(0,0,testState)
      "have a Cell with state 9" in {
        field.getCell(0,0).getState() should be(testState)
      }
    }
  }

  "A Field" when {
    "setMinesState" should {
      val field = new Field(2,1,1)
      field.setMinesState(0,0)
      "be a mine in the Cell" in {
        field.getCell(0,0).getState() should be(1)
        field.getCell(1,0).getState() should be(MINE)
      }
    }
  }

}
