import de.htwg.se.Minesweeper.util.Observable
import de.htwg.se.Minesweeper.util.Observer

class TestObject extends Observer {
  def update = println("Ping")
}
object ObserverPattern {
  val observable = new Observable
  val observer1 = new TestObject
  val observer2 = new TestObject
  observable.add(observer1)
  observable.add(observer2)
  observable.notifyObservers

  observable.remove(observer1)
  observable.notifyObservers
  observable.remove(observer2)
  observable.notifyObservers
}