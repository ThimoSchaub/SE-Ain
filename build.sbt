name          := "Minesweeper"
organization  := "de.htwg.se"
version       := "0.0.2"
scalaVersion  := "2.12.4"
scalacOptions := Seq("-unchecked", "-feature", "-deprecation", "-encoding", "utf8")

resolvers += Resolver.jcenterRepo

libraryDependencies ++= {
  val scalaTestV       = "3.0.4"
  Seq(
    "org.scalatest" %% "scalatest"                   % scalaTestV       % "test"
  )
}

libraryDependencies += "junit" % "junit" % "4.8" % "test"

libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.12" % "2.0.1"

libraryDependencies += "io.github.todokr" %% "emojipolation" % "0.2.0"

libraryDependencies += "com.google.inject" % "guice" % "4.1.0"

libraryDependencies += "net.codingwell" %% "scala-guice" % "4.1.0"

libraryDependencies += "org.scala-lang.modules" % "scala-xml_2.12" % "1.0.6"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.6"

libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"