package de.htwg.se.Minesweeper.model.fileIoComponent

import de.htwg.se.Minesweeper.model.fieldComponent.FieldInterface

trait FileIOInterface {

  def load:Option[FieldInterface]

  def save(field:FieldInterface):Unit

}
