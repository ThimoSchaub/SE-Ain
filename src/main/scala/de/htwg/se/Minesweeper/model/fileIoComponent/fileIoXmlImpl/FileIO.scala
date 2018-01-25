package de.htwg.se.Minesweeper.model.fileIoComponent.fileIoXmlImpl

import java.io.File

import com.google.inject.Guice
import com.google.inject.name.Names
import net.codingwell.scalaguice.InjectorExtensions._
import de.htwg.se.Minesweeper.MinesweeperModule
import de.htwg.se.Minesweeper.model.fileIoComponent.FileIOInterface
import de.htwg.se.Minesweeper.model.fieldComponent.FieldInterface

import scala.xml.PrettyPrinter

class FileIO extends FileIOInterface {

  override def load: Option[FieldInterface] = {
    var fieldOption: Option[FieldInterface] = None
    val sourcex = new File ("field.xml")
    if(sourcex.exists()){
    val file = scala.xml.XML.loadFile("field.xml")
    val sizeAttr = (file \\ "field" \ "@size")
    val size = sizeAttr.text.toInt
    val injector = Guice.createInjector(new MinesweeperModule)
    size match {
      case 5 => fieldOption = Some(injector.instance[FieldInterface](Names.named("easy")))
      case 10 => fieldOption = Some(injector.instance[FieldInterface](Names.named("medium")))
      case 15 => fieldOption = Some(injector.instance[FieldInterface](Names.named("heavy")))
      case _ =>
    }
      val cellNodes= (file \\ "cell")
      fieldOption match {
        case Some(field)=> {
          var _field = field
          for (cell <- cellNodes) {
            val row: Int = (cell \ "@row").text.toInt
            val col: Int = (cell \ "@col").text.toInt
            var toSplit: String = cell.text
            val splitted = toSplit.split(" ")

            val isVisible: Boolean = splitted(1).toBoolean
            val state: Int = splitted(2).toInt
            val flag: Boolean = splitted(3).toBoolean
            _field = _field.set(row, col, isVisible, state, flag)
          }
          fieldOption = Some(_field)
        }
        case None =>
      }
    }


    fieldOption
  }

  def save(field:FieldInterface):Unit = saveString(field)

  def saveXML(field:FieldInterface):Unit = {
    scala.xml.XML.save("field.xml", fieldToXml(field))
  }

  def saveString(field:FieldInterface): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("field.xml" ))
    val prettyPrinter = new PrettyPrinter(120,4)
    val xml = prettyPrinter.format(fieldToXml(field))
    pw.write(xml)
    pw.close
  }
  def fieldToXml(field:FieldInterface) = {
    <field size ={field.getFieldSizeX.toString}>
      {
      for {
        row <- 0 until field.getFieldSizeX
        col <- 0 until field.getFieldSizeX
      } yield cellToXml(field, row, col)
      }
    </field>
  }

  def cellToXml(field:FieldInterface, row:Int, col:Int) ={
    <cell row ={row.toString} col={col.toString}>
      {field.getCell(row, col).getVisibility()}
      {field.getCell(row, col).getState()}
      {field.getCell(row, col).getFlag()}
    </cell>
  }

}
