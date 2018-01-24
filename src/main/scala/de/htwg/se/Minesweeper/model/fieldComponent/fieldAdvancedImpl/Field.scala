package de.htwg.se.Minesweeper.model.fieldComponent.fieldAdvancedImpl

import com.google.inject.Inject
import com.google.inject.name.Named
import de.htwg.se.Minesweeper.model.fieldComponent.FieldInterface
import de.htwg.se.Minesweeper.model.fieldComponent.fieldBaseImpl.{Field => BaseField}

class Field @Inject()(@Named("DefaultSize")size:Int,@Named("DefaultMine")mine:Int) extends BaseField(size,size,mine) {

}
