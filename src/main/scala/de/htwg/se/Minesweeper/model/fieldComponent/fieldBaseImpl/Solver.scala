package de.htwg.se.Minesweeper.model.fieldComponent.fieldBaseImpl


class Solver(field:Field) {


   def solve: Tuple2[Boolean, Field] = solve(0)

  def solve(index: Int): Tuple2[Boolean, Field] = {
    field.allVisible
    var res: Tuple2[Boolean, Field] = (true, field)
    return res
  }
}