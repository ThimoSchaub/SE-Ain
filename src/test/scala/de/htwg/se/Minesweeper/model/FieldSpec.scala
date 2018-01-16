package de.htwg.se.Minesweeper.model
import org.scalatest.{Matchers, WordSpec}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FieldSpec extends WordSpec with Matchers {
  "A Field" when { "new" should {
    val field = new Field(1,1,0)
    "have a fieldsizex"  in {
      field.fieldsizex should be(1)
    }
    "have a fieldsizey"  in {
      field.fieldsizey should be(1)
    }
    "have a mine"  in {
      field.mines should be(0)
    }
    "have a getCell" in {
      field.getCell(0, 0).getState() should be(0)
    }
    "have a setCell" in {
      val cell1 = new Cell()
      cell1.setState(2)
      field.setCell(0, 0, 2)
      field.getCell(0, 0).equals(cell1) should be(true)
    }
    "have a nice String representation" in {
      field.toString should be("\n+---+\n|   |\n+---+\n")
    }
    "have the Cell with state Flag" in {
      field.performAction(0,0,3)
      field.getCell(0,0).getFlag() should be(true)
    }
    "have the Cell with state" in {
      field.performAction(0,0,2)
      field.getCell(0,0).getVisibility() should be(true)
    }
  "A Field" when { "set_Mines_state" should {
    val field = new Field(3,3,9)
    field.allVisible
    "have a String reprsentaion" in {
      field.toString should be("\n+---+---+---+\n| emoji\":bomb:\" | emoji\":bomb:\" | emoji\":bomb:\" |\n+---+---+---+\n| emoji\":bomb:\" | emoji\":bomb:\" | emoji\":bomb:\" " +
        "|\n+---+---+---+\n| emoji\":bomb:\" | emoji\":bomb:\" | emoji\":bomb:\" |\n+---+---+---+\n")
    }
  }
    "A Field" when { "set_Mines_state" should {
      val field = new Field(1,2,1)
      field.allVisible
      "have a Cell with state 1" in {
        field.getCell(0, 0).getState() == 1 || field.getCell(0, 1).getState() == 1 should be(true)
      }
      }
    }

  }
  }}


}
