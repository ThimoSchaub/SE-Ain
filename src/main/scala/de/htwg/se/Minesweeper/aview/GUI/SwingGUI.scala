package de.htwg.se.Minesweeper.aview.GUI

import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.Minesweeper.controller._
import scala.io.Source._

class CellClicked(val row: Int, val column: Int) extends Event

class SwingGUI (controller: Controller) extends Frame {

  listenTo()

  title = "Minesweeper"

}
