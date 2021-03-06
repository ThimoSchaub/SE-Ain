package de.htwg.se.Minesweeper.aview.gui


import com.google.inject.Inject
import com.google.inject.name.Named

import scala.swing._
import scala.swing.event._
import de.htwg.se.Minesweeper.controller.controllerComponent.{CellChange, ControllerInterface, FieldSizeChange}
class CellClicked(val row: Int, val column: Int) extends Event

class SwingGUI (controller: ControllerInterface) extends Frame{

  listenTo(controller)
  title = "Minesweeper"
  var cells = Array.ofDim[CellPanel](controller.fieldsizey, controller.fieldsizex)


  def gridPanel = new GridPanel(controller.blockSize, controller.blockSize) {
        background = java.awt.Color.BLACK
        preferredSize= new Dimension(51*controller.blockSize,51*controller.blockSize)

        for {
          row <- 0 until controller.blockSize
          column <- 0 until controller.blockSize
        } {
          val x = row
          val y = column
          val cellPanel = new CellPanel(x, y, controller)
          cells(x)(y) = cellPanel
          contents += cellPanel
          listenTo(cellPanel)
    }
  }
  val statusline = new TextField(controller.statusText, 20)
  reactions +={
    case event:FieldSizeChange=>{resize(event.newSize)
    redraw}
    case event:CellChange=>redraw
  }
  val mines = new Button("Restliche Minen: "+controller.getRest)
  contents = new BorderPanel {
    add(mines,BorderPanel.Position.North)
    add(gridPanel, BorderPanel.Position.Center)
    add(statusline, BorderPanel.Position.South)
  }

  menuBar = new MenuBar {
    contents += new Menu("File") {
      mnemonic = Key.F
      contents += new MenuItem(Action("New") { controller.createRandomField()
      redraw
      })
      contents += new MenuItem(Action("Save") {controller.save})
      contents += new MenuItem(Action("Load") {controller.load
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

    contents += new Menu("Options") {
      mnemonic = Key.O
      contents += new MenuItem(Action("Easy") {
        if(controller.fieldsizex == controller.small){
          controller.createRandomField()
        }else {
          controller.resize(controller.small)
        }
        redraw
        })
      contents += new MenuItem(Action("Medium") { if(controller.fieldsizex == controller.normal){
        controller.createRandomField()
      }else {
        controller.resize(controller.normal)
      }
        redraw
      })
      contents += new MenuItem(Action("Hard") { if(controller.fieldsizex == controller.big){
        controller.createRandomField()
      }else {
        controller.resize(controller.big)
      }
      redraw
      })

    }

    contents += mines
  }
  resizable_=(false)
  visible = true
  redraw

  def resize(fieldSize: Int) = {
    cells = Array.ofDim[CellPanel](fieldSize, fieldSize)
    contents = new BorderPanel {
      add(gridPanel, BorderPanel.Position.Center)
      add(statusline, BorderPanel.Position.South)
    }
  }
  def redraw = {
    for {
      row <- 0 until controller.fieldsizex
      column <- 0 until controller.fieldsizey
    } cells(row)(column).redraw
    mines.text_=("Restliche Minen:"+controller.getRest)
    statusline.text = controller.statusText
    repaint
  }
}
