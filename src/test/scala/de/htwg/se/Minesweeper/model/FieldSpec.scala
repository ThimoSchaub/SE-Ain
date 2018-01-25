package de.htwg.se.Minesweeper.model
import de.htwg.se.Minesweeper.model.fieldComponent.fieldBaseImpl.{Cell, Field}
import org.scalatest.{Matchers, WordSpec}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import io.github.todokr.Emojipolation._

@RunWith(classOf[JUnitRunner])
class FieldSpec extends WordSpec with Matchers {
  "A Field" when { "new" should {
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
    }
    /*"have a setCell" in {
      val cell1 = new Cell()
      cell1.setState(2)
      field.setCell(0, 0, 2)
      field.getCell(0, 0).equals(cell1) should be(true)
    }*/
    "have a nice String representation" in {
      field.toString should be("\n+---+\n|   |\n+---+\nRemaining mines: 0")
    }
    "have the Cell with state Flag" in {
      field.performAction(0,0,3,true)
      field.getCell(0,0).getFlag() should be(true)
    }
   /* "have the Cell with state" in {
      field.performAction(0,0,2)
      field.getCell(0,0).getVisibility() should be(true)
    }*/
  "A Field" when { "set_Mines_state" should {
    val field = new Field(1,1,1)
    field.allVisible
    field.getCell(0,0).setState(9)
    "have a String reprsentaion" in {
      field.toString should be("\n+---+\n|"+emoji":bomb:"+" |\n+---+\nRemaining mines: 1")
    }
  }
    "A Field" when { "set_Mines_state" should {
      val field = new Field(1,2,1)
      field.allVisible
      "have a Cell with state -1" in {
        field.getCell(0, 0).getState() == -1 && field.getCell(0, 1).getState() == -1 should be(true)
      }
      }
    }
  }
  }}


}
