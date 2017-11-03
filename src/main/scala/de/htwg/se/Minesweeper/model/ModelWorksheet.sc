import de.htwg.se.Minesweeper.model.Field
import de.htwg.se.Minesweeper.model.Cell
val cell1 = new Cell(4)


val cell2 = new Cell(5);

val feld = new Field(2,2,0)
feld.setCell(0,0,new Cell(1))
feld.setCell(0,1,new Cell(1))
feld.setCell(1,1,new Cell(1))
feld.setCell(1,0,new Cell(1))
println(feld)
println("+-+-+-+-+-+-+")
println("|0|x|x|x|x| |")
println("+-+-+-+-+-+-+")
println("| |x| |x|x| |")
println("+-+-+-+-+-+-+")
println("| x| x| x|x| | |")
println("+-+-+-+-+-+-+")
println("| | | | | | |")
println("+-+-+-+-+-+-+")

var c = scala.collection.immutable.Vector.empty
var cc = c:+5 :+6 :+7
cc.toString()
