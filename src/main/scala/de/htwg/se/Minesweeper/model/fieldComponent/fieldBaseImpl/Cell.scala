package de.htwg.se.Minesweeper.model.fieldComponent.fieldBaseImpl

import de.htwg.se.Minesweeper.model.fieldComponent.CellInterface
import io.github.todokr.Emojipolation._

class Cell() extends CellInterface {
  val MINE = 9
  var isVisible = false
  var state = -1
  var flag = false

  def getState(): Int = {
    return state
  }

  def setState(int: Int): Unit = {
    state = int
  }

  def getVisibility(): Boolean = {
    return isVisible
  }

  def setVisibility(visible: Boolean) = {
    isVisible = visible
  }

  def getFlag(): Boolean = {
    return flag
  }

  def setFlag(toFlag: Boolean): Unit = {
    flag = toFlag
  }

  def toggleFlag(): Unit = {
    flag = !flag
  }

  def check(): Boolean = {
    if (!getFlag()) {
      setVisibility(true)
      if (getState() == MINE && isVisible) {
        return true
      }
      return false
    }
    false
  }

  def undocheck(): Unit = {
    setVisibility(!getVisibility())
  }

  override def toString(): String = {
    if (isVisible) {
      if (getState() == 9) {
        return emoji":bomb:"
      }

      return state.toString
    }
    else if (flag) {
      return emoji":triangular_flag_on_post:"
    }
    else {
      return " "
    }
  }
}