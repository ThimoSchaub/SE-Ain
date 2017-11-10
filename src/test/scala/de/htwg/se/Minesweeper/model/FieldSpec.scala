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
      field.setCell(0, 0, cell1)
      field.getCell(0, 0).equals(cell1) should be(true)
    }
    "have a nice String representation" in {
      field.toString should be("\n+---+\n|   |\n+---+\n")
    }

  }}


}
