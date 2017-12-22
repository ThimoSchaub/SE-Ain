package de.htwg.se.Minesweeper.aview.gui

import de.htwg.se.Minesweeper.aview.gui.CellPanel
import scala.swing._
import scala.swing.Swing.LineBorder
import scala.swing.event._
import de.htwg.se.Minesweeper.controller._

import scala.io.Source._

class CellClicked(val row: Int, val column: Int) extends Event

class SwingGUI (controller: Controller) extends Frame {

  listenTo()

  title = "Minesweeper"
  var cells = Array.ofDim[CellPanel](controller.field.fieldsizey, controller.field.fieldsizex)


  def gridPanel = new GridPanel(controller.blockSize, controller.blockSize) {
        background = java.awt.Color.BLACK
        preferredSize= new Dimension(510,510)
        for {
          innerRow <- 0 until controller.blockSize
          innerColumn <- 0 until controller.blockSize
        } {
          val x = innerRow
          val y = innerColumn
          val cellPanel = new CellPanel(x, y, controller)
          cells(x)(y) = cellPanel
          contents += cellPanel
          listenTo(cellPanel)
    }
  }
  val statusline = new TextField(controller.statusText, 20)

  contents = new BorderPanel {
    add(gridPanel, BorderPanel.Position.Center)
    add(statusline, BorderPanel.Position.South)
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New") { controller.createRandomField()
      redraw
      })
      contents += new MenuItem(Action("Quit") { System.exit(0) })
    }
    contents += new Menu("Edit") {
      mnemonic = Key.E
      contents += new MenuItem(Action("Undo") { controller.undo
        redraw})
      contents += new MenuItem(Action("Redo") { controller.redo
        redraw})
    }
    contents += new Menu("Solve") {
      mnemonic = Key.S
      contents += new MenuItem(Action("Solve") { controller.solve
      redraw})
    }
//
//    contents += new Menu("Options") {
//      mnemonic = Key.O
//      contents += new MenuItem(Action("Show all candidates") { controller.toggleShowAllCandidates })
//      contents += new MenuItem(Action("Size 1*1") { controller.resize(1) })
//      contents += new MenuItem(Action("Size 4*4") { controller.resize(4) })
//      contents += new MenuItem(Action("Size 9*9") { controller.resize(9) })
//
//    }
  }

  visible = true
  redraw

  def resize(gridSize: Int) = {
    cells = Array.ofDim[CellPanel](controller.field.fieldsizex, controller.field.fieldsizey)
    contents = new BorderPanel {
      add(gridPanel, BorderPanel.Position.Center)
      add(statusline, BorderPanel.Position.South)
    }
  }
  def redraw = {
    for {
      row <- 0 until controller.field.fieldsizex
      column <- 0 until controller.field.fieldsizey
    } cells(row)(column).redraw
    statusline.text = controller.statusText
    repaint
  }
}
