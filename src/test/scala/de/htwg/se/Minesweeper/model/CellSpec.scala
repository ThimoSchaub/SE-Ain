package de.htwg.se.Minesweeper.model

import de.htwg.se.Minesweeper.model.fieldComponent.fieldBaseImpl.Cell
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}
import io.github.todokr.Emojipolation._

@RunWith(classOf[JUnitRunner])
class CellSpec extends WordSpec with Matchers {
  "A Cell" when { "new" should {
    val cell = new Cell()
    "have a state"  in {
      cell.getState should be(-1)
    }
    "have a visibility" in {
      cell.getVisibility() should be(false)
    }
    "have a flag status" in {
      cell.getFlag() should be(false)
    }
    "have a nice String representation" in {
      cell.toString should be(" ")
    }
  }}

  "A Cell" when { "setVisibility" should {
    val cell = new Cell()
    cell.setVisibility(true)
    "have a Visibility" in {
      cell.getVisibility() should be(true)
    }
  }}

  "A Cell" when { "setState" should {
    val cell = new Cell()
    cell.setState(3)
    "have a State" in {
      cell.getState() should be(3)
    }
  }}

  "A Cell" when { "setFlag" should {
    val cell = new Cell()
    cell.setFlag(true)
    "have a flag status" in {
      cell.getFlag() should be(true)
    }
    "have a String representation" in {
      cell.toString() should be(emoji":triangular_flag_on_post:")
    }
    "have a toggleable flag status" in {
      cell.toggleFlag()
      cell.getFlag() should be (false)
    }
  }}

  "A Cell" when { "check" should {
    val cell = new Cell()
    cell.check()
    "have a visibility" in {
      cell.getVisibility() should be(true)
    }
    "have an undo for the check" in {
      cell.undoCheck()
      cell.getVisibility() should be(false)
    }
  }}
}