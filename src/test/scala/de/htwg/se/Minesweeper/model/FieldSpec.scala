package de.htwg.se.Minesweeper.model
import org.scalatest.{Matchers, WordSpec}
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class FieldSpec extends WordSpec with Matchers {
  "A Field" when { "new" should {
    val field = Field(1,1,0)
    "have a fieldsizex"  in {
      field.fieldsizex should be(1)
    }
    "have a fieldsizey"  in {
      field.fieldsizey should be(1)
    }
    "have a mine"  in {
      field.mines should be(0)
    }

  }}


}
