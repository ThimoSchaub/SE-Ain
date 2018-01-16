package de.htwg.se.Minesweeper.controller


import de.htwg.se.Minesweeper.controller.GameStatus.SOLVED
import de.htwg.se.Minesweeper.model.fieldComponent.fieldBaseImpl.{Field, Solver}
import de.htwg.se.Minesweeper.util.Command


class SolveCommand(controller: Controller) extends Command {
  var memento: Field = controller.field
  override def doStep: Unit = {
    memento = controller.field
    val (success, newfield) = new Solver(controller.field).solve
    if (success) controller.gameStatus = SOLVED
    controller.field = newfield
  }

  override def undoStep: Unit = {
    val new_memento = controller.field
    controller.field = memento
    memento = new_memento
  }

  override def redoStep: Unit = {
    val new_memento = controller.field
    controller.field = memento
    memento = new_memento
  }

}