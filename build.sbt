lazy val root = (project in file(".")).
  settings(
    name := "rubiks",
    scalaVersion := "2.11.8",
    scalacOptions += "-feature",
    libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test",
    libraryDependencies += "org.scalamock" %% "scalamock-scalatest-support" % "3.2.2" % "test"
  )
