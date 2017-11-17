package de.htwg.se.Minesweeper.model

class Cell() {
  val MINE = 9
  var isVisible = false
  var state = 0
  var flag = false

  def getState(): Int = {
    return state
  }
  def setState(int: Int): Unit ={
    state = int
  }

  def getVisibility():Boolean={
    return isVisible
  }
  def setVisibility(visible:Boolean)={
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

  def check(): Unit = {
    if (getState() == MINE) {
      // LOSE!
    }
    else {
      setVisibility(true)
    }
  }

  override def toString(): String = {
    if (isVisible) {
      return state.toString
    }
    else if (flag) {
      return "F"
    }
    else {
      return " "
    }
  }
}