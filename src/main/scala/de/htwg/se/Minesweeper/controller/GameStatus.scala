package de.htwg.se.Minesweeper.controller


object GameStatus extends Enumeration{
  type GameStatus = Value
  val IDLE, NEW, SOLVED, UNDO, REDO = Value

  val map = Map[GameStatus, String](
    IDLE -> "",
    NEW -> "A nwe game was created",
    SOLVED ->"Game successfully solved",
    UNDO -> "Undone one step",
    REDO -> "Redone one step")

  def message(gameStatus: GameStatus) = {
    map(gameStatus)
  }

}