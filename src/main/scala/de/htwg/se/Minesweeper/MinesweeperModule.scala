package de.htwg.se.Minesweeper

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.Minesweeper.controller.controllerComponent._
import de.htwg.se.Minesweeper.model.fileIoComponent._
import de.htwg.se.Minesweeper.model.fieldComponent.FieldInterface
import de.htwg.se.Minesweeper.model.fieldComponent.fieldBaseImpl.Field

import net.codingwell.scalaguice.ScalaModule
class MinesweeperModule extends AbstractModule with ScalaModule {
  val defaultSize:Int = 13
  val defaultMine:Int = 27

  val small:Int = 8
  val normal:Int = 13
  val big:Int = 18

  def configure() = {
    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)
    bindConstant().annotatedWith(Names.named("DefaultMine")).to(defaultMine)
    bind[FieldInterface].to[Field]
    bind[ControllerInterface].to[controllerBaseImpl.Controller]

    bind[FieldInterface].annotatedWithName("easy").toInstance(new Field(small,small,10))
    bind[FieldInterface].annotatedWithName("medium").toInstance(new Field(normal,normal,27))
    bind[FieldInterface].annotatedWithName("hard").toInstance(new Field(big,big,68))

    bind[FileIOInterface].to[fileIoXmlImpl.FileIO]

  }
}
