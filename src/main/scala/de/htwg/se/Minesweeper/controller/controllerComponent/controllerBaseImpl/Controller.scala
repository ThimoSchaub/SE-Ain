package de.htwg.se.Minesweeper.controller.controllerComponent.controllerBaseImpl


import com.google.inject.name.Names
import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.Minesweeper.MinesweeperModule
import de.htwg.se.Minesweeper.controller.controllerComponent.{CellChange, ControllerInterface, FieldSizeChange, GameStatus}
import de.htwg.se.Minesweeper.controller.controllerComponent.GameStatus.{GameStatus, _}
import de.htwg.se.Minesweeper.model.fieldComponent.FieldInterface
import de.htwg.se.Minesweeper.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.Minesweeper.model.fileIoComponent.FileIOInterface
import de.htwg.se.Minesweeper.util.UndoManager

import scala.swing.Publisher
import scala.swing.event.Event

class Controller @Inject() (var field:FieldInterface) extends ControllerInterface {

  private val undoManager = new UndoManager
  var gameStatus: GameStatus = IDLE
  val injector = Guice.createInjector(new MinesweeperModule)
  val fileIo = injector.instance[FileIOInterface]

  def createRandomField():Unit = {
    field.getFieldSizeX match {
      case 5 => field = injector.instance[FieldInterface](Names.named("easy"))
      case 10 => field = injector.instance[FieldInterface](Names.named("medium"))
      case 15 => field = injector.instance[FieldInterface](Names.named("hard"))
      case _ =>
    }
    field.visibleCells = 0
    gameStatus = NEW
    publish(new CellChange)
  }

  def fieldToString: String = field.toString

  def set(row: Int, col: Int, action: Int): Unit = {
    if(gameStatus != LOST && gameStatus != SOLVED){
      undoManager.doStep(new SetCommand(row, col, action, this))
      if (field.checkSolved){
        gameStatus = SOLVED
      }
      if (field.checkMine){
        gameStatus = LOST
      }
      publish(new CellChange)
    }
  }

  def isSet(row: Int, col:Int):Boolean = field.getCell(row, col).getVisibility()

  def solve: Unit = {
    if(field.getCell(0,0).getState() == -1) {
      field.setMinesState(0,0)
    }
    undoManager.doStep(new SolveCommand(this))
    gameStatus = SOLVED
    publish(new CellChange)
  }

  def save: Unit = {
    fileIo.save(field)
    gameStatus = SAVED
    publish(new CellChange)
  }

  def load: Unit = {
    val fieldOption = fileIo.load
    fieldOption match {
      case None => {
        field = new Field(fieldsizex, fieldsizey, 0)
        gameStatus = COULDNOTLOAD
      }
      case Some(_field) => {
        field = _field
        gameStatus = LOADED
      }
    }
  }

  def toJson = field.toJson

  def statusText:String = GameStatus.message(gameStatus)

  def undo: Unit = {
    undoManager.undoStep
    gameStatus = UNDO
    publish(new CellChange)
  }

  def redo: Unit = {
    undoManager.redoStep
    gameStatus = REDO
    publish(new CellChange)
  }

  def getFieldsize:Int = field.getFieldSizeX*field.getFieldSizeY

  def blockSize:Int=Math.sqrt(getFieldsize).toInt

  def cell(row: Int, col: Int) = field.getCell(row, col)

  override def fieldsizex = field.getFieldSizeX

  override def fieldsizey = field.getFieldSizeY

  override def getRest = field.getRestMine

  override def resize(size: Int): Unit = {
    size match {
      case 5 => field = injector.instance[FieldInterface](Names.named("easy"))
      case 10 => field = injector.instance[FieldInterface](Names.named("medium"))
      case 15 => field = injector.instance[FieldInterface](Names.named("hard"))
      case  _=>
    }
    gameStatus = NEW
    publish(new FieldSizeChange(size))
  }
}

