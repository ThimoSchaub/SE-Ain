[![Build Status](https://travis-ci.org/ThimoSchaub/SE-Ain.png?branch=master)](https://travis-ci.org/ThimoSchaub/SE-Ain)
[![Coverage Status](https://coveralls.io/repos/github/ThimoSchaub/SE-Ain/badge.png?branch=master)](https://coveralls.io/github/ThimoSchaub/SE-Ain?branch=master) 

Minesweeper
=========================

This is a seed project to create a basic scala project as used in the
class Software Engineering at the University of Applied Science HTWG Konstanz

## Goals of project

* learning Scala
* Design Patterns
    * Observer Pattern / Publisher
    * Singleton Pattern
    * Command Pattern
    * Memento
* Tests & Specs
* Build Tool & Continuous Integratnion
    * Travis
    * sbt

## Game overview

**How to play**

* Start game
    * Just start the programm. It will automaticly load a default field to play on.
* Rules
    * Lef mouse click on a cell (GUI) or choose a coordinate (GUI) to check wether there is a mine or by how many mines this cell is surrounded
    * If a cell without mines next to it is checked it will check all the cells next to it
    * Right click to mark a cell as a mine. This is visualized by a flag
    * If a cell is checked that is already visible and there are as many flags placed around it as it has mines in its surrounding, all cells around it will be checked (except the ones with a flag on it)
    * The field is solved if all cells that are not mines have been checked and are visible
