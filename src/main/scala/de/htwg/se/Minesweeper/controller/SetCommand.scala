package de.htwg.se.Minesweeper.controller

import de.htwg.se.Minesweeper.util.Command

class SetCommand(row:Int, col: Int, action:Int, controller: Controller) extends Command {
  override def doStep: Unit =   controller.field = controller.field.performAction(row, col, action)

  override def undoStep: Unit = controller.field = controller.field.performAction(row, col, action)

  override def redoStep: Unit = controller.field = controller.field.performAction(row, col, action)
}