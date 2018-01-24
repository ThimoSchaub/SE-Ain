package de.htwg.se.Minesweeper.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.Minesweeper.controller.controllerComponent.GameStatus.SOLVED
import de.htwg.se.Minesweeper.model.fieldComponent.FieldInterface
import de.htwg.se.Minesweeper.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.Minesweeper.util.Command


class SolveCommand(controller: Controller) extends Command {
  var memento: FieldInterface = controller.field
  override def doStep: Unit = {
    memento = controller.field
    controller.field.allVisible
    controller.gameStatus = SOLVED
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