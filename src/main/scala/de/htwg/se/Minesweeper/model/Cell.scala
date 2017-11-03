package de.htwg.se.Minesweeper.model

case class Cell(state: Int) {
  var isVisible = false;

  def getState: Int = state;

  /*var out = "";
  if (state == 0) {
    out = "X";
  }else if(state == -1){

  }else if(state > 8){

  }else*/
  def setVisibility(visible:Boolean)={
    isVisible = visible
  }
  override def toString: String = {
    if (isVisible) {
      return state.toString
    }
    else {
      return " "
    }
  }
}