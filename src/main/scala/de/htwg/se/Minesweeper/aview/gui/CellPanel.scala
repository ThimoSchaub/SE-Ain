package de.htwg.se.Minesweeper.aview.gui

import scala.swing._
import javax.swing.table._

import de.htwg.se.Minesweeper.controller.controllerComponent.ControllerInterface

import scala.swing.event._


class CellPanel(row: Int, column: Int, controller: ControllerInterface) extends FlowPanel {

  val cellColor = new Color(224, 224, 255)

  def myCell = controller.cell(row, column)

  def cellText(row: Int, col: Int) =  controller.cell(row, column).toString

  val label =
    new Label {
      text = cellText(row, column)
      font = new Font("TRUETYPE_FONT", 1, 36)
    }

  val cell = new BoxPanel(Orientation.Horizontal) {
    contents += label
    preferredSize = new Dimension(51,51)
    background = cellColor
    border = Swing.BeveledBorder(Swing.Raised)
    listenTo(mouse.clicks)
    reactions += {
      case evt @ MouseClicked(src, pt, mod, clicks, pops) => {
        controller.set(row,column,evt.peer.getButton())
        label.text = cellText(row, column)
        repaint
      }
      case _ =>{
        label.text = cellText(row, column)
        repaint
      }
    }
  }

  def redraw = {
    label.text = cellText(row, column)
    setBackground(cell)
    contents += cell
    repaint
  }

  def setBackground(p: Panel) = p.background = cellColor

}
