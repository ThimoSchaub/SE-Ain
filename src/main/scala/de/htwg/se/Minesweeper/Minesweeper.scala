package de.htwg.se.Minesweeper

import de.htwg.se.Minesweeper.model.Player

object Minesweeper {
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Hello, " + student.name)
  }
}
