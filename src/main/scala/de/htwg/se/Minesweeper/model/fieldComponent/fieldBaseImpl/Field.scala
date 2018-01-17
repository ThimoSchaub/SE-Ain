package de.htwg.se.Minesweeper.model.fieldComponent.fieldBaseImpl

case class Field(var x:Int,var y:Int,var mines:Int) {
  var checkmine: Boolean = false

  val fieldsizex = x
  val fieldsizey = y
  var mine = mines
  var visiblechells = 0
  val field = Array.ofDim[Cell](x, y)
  for (
    row <- 0 until fieldsizex;
    col <- 0 until fieldsizey
  ) {
    field(row)(col) = new Cell()
  }


  def getFieldsizex : Int = {
    return fieldsizex
  }

  def getFieldsizey: Int = {
    return fieldsizey
  }

  def getMines: Int = {
    return mine
  }

  def getCell(x: Int, y: Int): Cell = {
    if (x < 0 || x >= fieldsizex || y < 0 || y >= fieldsizey) {
      return new Cell()
    }
    field(x)(y)
  }

  def setCell(x: Int, y: Int, state: Int): Unit = {
      field(x)(y).setState(state)
  }

  def performAction(row: Int, col: Int, action: Int):Field= {
    action match {
      case 3 => field(row)(col).toggleFlag()
      case 1 => {
        if (visiblechells==0){
          set_Mines_state(row,col)
          getCell(row,col).check()
          visiblechells+=1
          performAction(row,col,1)
        }else if (getCell(row,col).getState()==0&&(!getCell(row,col).isVisible)){
          getCell(row,col).check()
          visiblechells +=1
          for(
            x <- row - 1 until row + 2;
            y <- col - 1 until col + 2
          ){
            if(0<=x&&0<=y&&y<=fieldsizey-1&&x<=fieldsizex-1){
            performAction(x,y,1)}
          }
        }else{
          field(row)(col).check()
          if (field(row)(col).check()){
            allVisible
            checkmine=true
          }
        }

      }
      case 0 => {
        field(row)(col).undocheck()
        visiblechells-=1
      }
      case _ =>
    }
    return this
  }

  def checksolved: Boolean={
    for (
      row <- 0 until fieldsizex;
      col <- 0 until fieldsizey
    ) {
      if ((!field(row)(col).getVisibility())//zelle nicht sichtbar
        &&field(row)(col).getState()!=9)//keine mine
      {
        return false
      }
    }
      return true
  }

  def allVisible: Unit = {

    for (
      row <- 0 until fieldsizex;
      col <- 0 until fieldsizey
    ) {

      field(row)(col).setVisibility(true)
    }
  }

  override def toString: String = {

    val lineseparator = ("+---") * y + "+\n"
    val line = ("|" + ("toreplace")) * y + "|\n"
    var box = "\n" + (lineseparator + (line)) * x + lineseparator
    for {
      row <- 0 until fieldsizex
      col <- 0 until fieldsizey
    } {
      if (field(row)(col).isVisible && field(row)(col).getState() == 9 || field(row)(col).flag) {
        (box = box.replaceFirst("toreplace", field(row)(col).toString() + " "))
      }
      else {
        (box = box.replaceFirst("toreplace", " " + field(row)(col).toString() + " "))
      }
    }
    box
  }

  def set_Mines_state(row:Int,col:Int): Unit = {
    var rand = scala.util.Random
    var mines_set = 0;
    while (
      mines_set < mine
    ) {
      val x =rand.nextInt(fieldsizex)
      val y = rand.nextInt(fieldsizey)
      var cell = getCell(x,y)
      if(cell.getState()!=9&&(!(x==row&&y==col))){
        cell.setState(9)
        mines_set += 1
      }
    }
    for (
      row <- 0 until fieldsizex;
      col <- 0 until fieldsizey
    ) {
      if (getCell(row, col).getState() != 9) {
        var count = 0;
        for (
          x <- row - 1 until row + 2;
          y <- col - 1 until col + 2
        ) {
          if (getCell(x, y).getState() == 9) {
            count += 1
          }
        }
        getCell(row, col).setState(count)
      }
    }
  }
}