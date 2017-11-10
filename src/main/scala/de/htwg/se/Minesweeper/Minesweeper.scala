package de.htwg.se.Minesweeper

import de.htwg.se.Minesweeper.model.{Cell, Field, Player}

object Minesweeper {
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Hello, " + student.name)
    val feld = new Field(4,4,0)
    for(
      row <- 0 until feld.fieldsizex;
      col <- 0 until feld.fieldsizey
    ){
      feld.setCell(row,col,new Cell())
    }


    feld.getCell(1,1).setVisibility(true)
    println(feld)
  }
}
