package de.htwg.se.Minesweeper

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.Minesweeper.controller.controllerComponent._
import de.htwg.se.Minesweeper.model.fieldComponent.FieldInterface
import de.htwg.se.Minesweeper.model.fieldComponent.fieldBaseImpl.Field
import net.codingwell.scalaguice.ScalaModule
class MinesweeperModule extends AbstractModule with ScalaModule {
  val defaultSize:Int = 10
  val defaultMine:Int = 20

  def configure() = {
    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)
    bindConstant().annotatedWith(Names.named("DefaultMine")).to(defaultMine)
    bind[FieldInterface].to[Field]
    bind[ControllerInterface].to[controllerBaseImpl.Controller]

    bind[FieldInterface].annotatedWithName("easy").toInstance(new Field(5,5,3))
    bind[FieldInterface].annotatedWithName("medium").toInstance(new Field(10,10,20))
    bind[FieldInterface].annotatedWithName("hard").toInstance(new Field(15,15,40))

    //bind[FileIOInterface].to[fileIoJsonImpl.FileIO]

  }
}
