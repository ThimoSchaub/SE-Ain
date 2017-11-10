package de.htwg.se.Minesweeper.model

class Cell() {
  var isVisible = false
  var state = 0

  def getState(): Int = {
    return state
  }

  def setState(int: Int): Unit ={
    state = int
  }
  def setVisibility(visible:Boolean)={
    isVisible = visible
  }

  def getVisibility():Boolean={
    return isVisible
  }

  override def toString(): String = {
    if (isVisible) {
      return state.toString
    }
    else {
      return " "
    }
  }
}