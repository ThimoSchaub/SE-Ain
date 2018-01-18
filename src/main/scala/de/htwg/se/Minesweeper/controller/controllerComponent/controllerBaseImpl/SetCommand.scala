package de.htwg.se.Minesweeper.controller.controllerComponent.controllerBaseImpl


import de.htwg.se.Minesweeper.util.Command

class SetCommand(row:Int, col: Int, action:Int, controller: Controller) extends Command {
  override def doStep: Unit =   controller.field = controller.field.performAction(row, col, action)

  override def undoStep: Unit = controller.field = {
    if (action == 1) {
      controller.field.performAction(row, col, 0)
    }
    else {
      controller.field.performAction(row, col, action)
    }
  }

  override def redoStep: Unit = controller.field = controller.field.performAction(row, col, action)
}