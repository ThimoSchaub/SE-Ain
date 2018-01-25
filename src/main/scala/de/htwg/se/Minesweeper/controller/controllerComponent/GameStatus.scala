package de.htwg.se.Minesweeper.controller.controllerComponent

object GameStatus extends Enumeration{
  type GameStatus = Value
  val IDLE, NEW, SOLVED, UNDO, REDO, LOST, SAVED, LOADED, COULDNOTLOAD,RESIZED = Value

  val map = Map[GameStatus, String](
    IDLE -> "",
    NEW -> "A new game was created",
    SAVED -> "Game saved",
    LOADED -> "Game was loaded",
    COULDNOTLOAD -> "File could not be loaded. Sorry :(",
    SOLVED ->"Game successfully solved",
    UNDO -> "Undone one step",
    REDO -> "Redone one step",
    LOST -> "GAME OVER",
    RESIZED->"GAMEDIFFICULTY WAS CHANGED")

  def message(gameStatus: GameStatus) = {
    map(gameStatus)
  }

}