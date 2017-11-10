package de.htwg.se.Minesweeper.model

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}

@RunWith(classOf[JUnitRunner])
class CellSpec extends WordSpec with Matchers {
  "A Cell" when { "new" should {
    val cell = new Cell()
    "have a state"  in {
      cell.getState should be(0)
    }
    "have a Visibility" in {
      cell.getVisibility() should be(false)
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


}