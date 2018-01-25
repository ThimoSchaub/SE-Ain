package de.htwg.se.Minesweeper.model.fileIoComponent.fileIoJsonImpl

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.Minesweeper.MinesweeperModule
import de.htwg.se.Minesweeper.model.fieldComponent.{CellInterface, FieldInterface}
import de.htwg.se.Minesweeper.model.fileIoComponent.FileIOInterface
import play.api.libs.json._
import scala.io.Source

class FileIO extends FileIOInterface {

  override def load: Option[FieldInterface] = {
    var fieldOption: Option[FieldInterface] = None
    val source: String = Source.fromFile("field.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val size = (json \ "field" \ "size").get.toString.toInt
    println("oben")
    println("|"+size+"|")
    println("unten")
    val injector = Guice.createInjector(new MinesweeperModule)
    size match {
      case 5 => fieldOption = Some(injector.instance[FieldInterface](Names.named("easy")))
      case 10 => fieldOption = Some(injector.instance[FieldInterface](Names.named("medium")))
      case 15 => fieldOption = Some(injector.instance[FieldInterface](Names.named("heavy")))
      case _ =>
    }
    fieldOption match {
      case Some(field) => {
        var _field = field
        for (index <- 0 until size * size) {
          val row = (json \\ "row") (index).as[Int]
          val col = (json \\ "col") (index).as[Int]
          val cell = (json \\ "cell") (index)
          val isVisible = (cell \ "isVisible").as[Boolean]
          val state = (cell \ "state").as[Int]
          val flag = (cell \ "flag").as[Boolean]
          _field = _field.set(row, col, isVisible, state, flag)
        }
        fieldOption=Some(_field)
      }
      case None =>
    }
    fieldOption
  }

  override def save(field: FieldInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("field.json"))
    pw.write(Json.prettyPrint(fieldToJson(field)))
    pw.close
  }

  def fieldToJson(field: FieldInterface) = field.toJson

}
