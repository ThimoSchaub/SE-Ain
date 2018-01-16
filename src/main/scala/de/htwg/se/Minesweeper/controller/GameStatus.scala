package de.htwg.se.Minesweeper.controller


object GameStatus extends Enumeration{
  type GameStatus = Value
  val IDLE, NEW, SOLVED, UNDO, REDO,LOST = Value

  val map = Map[GameStatus, String](
    IDLE -> "",
    NEW -> "A new game was created",
    SOLVED ->"Game successfully solved",
    UNDO -> "Undone one step",
    REDO -> "Redone one step",
    LOST -> "GAME OVER")

  def message(gameStatus: GameStatus) = {
    map(gameStatus)
  }

}