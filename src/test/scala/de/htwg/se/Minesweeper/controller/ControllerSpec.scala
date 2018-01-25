package de.htwg.se.Minesweeper.controller.controllerComponent.controllerBaseImpl


import java.io.File

import de.htwg.se.Minesweeper.model.fieldComponent.fieldBaseImpl.Field
import de.htwg.se.Minesweeper.model.fieldComponent.fieldBaseImpl.Cell
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.{Matchers, WordSpec}
import de.htwg.se.Minesweeper.controller.controllerComponent.GameStatus.{GameStatus, _}

import scala.io.{BufferedSource, Source}
import scala.language.reflectiveCalls


@RunWith(classOf[JUnitRunner])
class ControllerSpec extends WordSpec with Matchers {

  "A Controller" when {
    "empty" should {
      var smallField = new Field(1,1,0)
      val controller = new Controller(smallField)
      " solving a field correctly" in {
        controller.field.getCell(0, 0).getVisibility() should be(false)
        controller.field.checkMine should be(false)
        controller.solve
        controller.field.getCell(0, 0).getVisibility() should be(true)
        controller.gameStatus should be(SOLVED)
      }
      "create random Field" in {
        controller.field = new Field(8,8,10)
        controller.createRandomField()
        controller.field.getRestMine should be (10)
        controller.field = new Field(13,13,10)
        controller.createRandomField()
        controller.field.getRestMine should be (27)
        controller.field = new Field(18,18,10)
        controller.createRandomField()
        controller.field.getRestMine should be (68)
        controller.field = new Field(11,11,12)
        controller.createRandomField()
        controller.field.getRestMine should be (12)
      }
      "have a fieldstring representation" in {
        controller.fieldToString.equals(controller.field.toString) should be (true)
      }
      "can change the field playable with set" in {
        controller.field = smallField
        controller.set(0,0,1)
        controller.field.getCell(0,0).getVisibility() should be (true)
        controller.gameStatus = IDLE
      }
      "can check is a cell is allready open" in {
        controller.isSet(0,0) should be (true)
      }
      "can save a file" in {
        controller.save
        val source = new File("field.json")
        val sourcex = new File ("field.xml")
        (source.exists||sourcex.exists) should be (true)
      }
      "can load a file" in {
        val fileTemp = new File("field.json")
        val sourcex = new File ("field.xml")
        controller.save
        val fie = controller.field
        controller.field = controller.field.setNew
        controller.load
        fie should be (controller.field)

        if (fileTemp.exists) {
          fileTemp.delete()
        }

        if (sourcex.exists) {
          sourcex.delete()
        }
        controller.load
        controller.gameStatus should be (COULDNOTLOAD)

      }
      "have a representation fopr a Statusmessage" in {
        controller.statusText should be ("File could not be loaded. Sorry :(")
      }
      "have a undo and a redo" in {
        controller.field = new Field(1,1,0)
        controller.set(0,0,1)
        controller.field.getCell(0,0).getVisibility() should be (true)
        controller.undo
        controller.field.getCell(0,0).getVisibility() should be (false)
        controller.redo
        controller.field.getCell(0,0).getVisibility() should be (true)
      }
      "have some nice variables" in {
        controller.fieldsizex should be (controller.field.getFieldSizeX)
        controller.fieldsizey should be (controller.field.getFieldSizeY)
        controller.blockSize should be (Math.sqrt(controller.getFieldsize).toInt)
        controller.cell(0,0) should be (controller.field.getCell(0,0))
        controller.getRest should be (controller.field.getRestMine)
      }
      "have a resize" in {
        controller.resize(8)
        controller.fieldsizey should be (8)
        controller.resize(13)
        controller.fieldsizey should be (13)
        controller.resize(18)
        controller.fieldsizey should be (18)
      }
    }
  }
}