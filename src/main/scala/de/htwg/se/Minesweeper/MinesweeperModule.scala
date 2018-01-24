package de.htwg.se.Minesweeper

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import net.codingwell.scalaguice.ScalaModule
import de.htwg.se.Minesweeper.controller.controllerComponent._
import de.htwg.se.Minesweeper.model.fileIoComponent._
import de.htwg.se.Minesweeper.model.fieldComponent.FieldInterface
import de.htwg.se.Minesweeper.model.fieldComponent.fieldBaseImpl.Field

class MinesweeperModule extends AbstractModule with ScalaModule {

  val defaultSize:Int = 9

  def configure() = {
    bindConstant().annotatedWith(Names.named("DefaultSize")).to(defaultSize)
//    bind[FieldInterface].to[Field]
 //   bind[ControllerInterface].to[controllerBaseImpl.Controller]

//    bind[FieldInterface].annotatedWithName("tiny").toInstance(new Field(5, 5, 3))
//    bind[FieldInterface].annotatedWithName("small").toInstance(new Field(10, 10, 20))
//    bind[FieldInterface].annotatedWithName("normal").toInstance(new Field(15, 15, 40))

    bind[FileIOInterface].to[fileIoXmlImpl.FileIO]

  }

}
