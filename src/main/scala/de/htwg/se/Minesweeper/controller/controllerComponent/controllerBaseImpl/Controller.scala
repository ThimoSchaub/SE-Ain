package de.htwg.se.Minesweeper.controller.controllerComponent.controllerBaseImpl


import com.google.inject.name.Names
import com.google.inject.name.Named
import com.google.inject.{Guice, Inject}
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.Minesweeper.MinesweeperModule
import de.htwg.se.Minesweeper.controller.controllerComponent.{CellChange, ControllerInterface, FieldSizeChange, GameStatus}
import de.htwg.se.Minesweeper.controller.controllerComponent.GameStatus.{GameStatus, _}
import de.htwg.se.Minesweeper.model.fieldComponent.FieldInterface
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
      case `small` => field = injector.instance[FieldInterface](Names.named("easy")).setNew
      case `normal` => field = injector.instance[FieldInterface](Names.named("medium")).setNew
      case `big` => field = injector.instance[FieldInterface](Names.named("hard")).setNew
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
      case Some(_field) => {
        resize(_field.getFieldSizeX)
        field = _field
        gameStatus = LOADED
      }
      case None => {
        field.getFieldSizeX match {
          case `small` => field = injector.instance[FieldInterface](Names.named("easy")).setNew
          case `normal` => field = injector.instance[FieldInterface](Names.named("medium")).setNew
          case `big` => field = injector.instance[FieldInterface](Names.named("hard")).setNew
          case _ =>
        }
        gameStatus = COULDNOTLOAD
      }
    }
    publish(new CellChange)
  }



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
      case `small` => field = injector.instance[FieldInterface](Names.named("easy")).setNew
      case `normal` => field = injector.instance[FieldInterface](Names.named("medium")).setNew
      case `big` => field = injector.instance[FieldInterface](Names.named("hard")).setNew
    }
    gameStatus = RESIZED
    publish(new FieldSizeChange(size))
  }
}

