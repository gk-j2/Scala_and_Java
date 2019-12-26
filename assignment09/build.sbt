import sbt.Keys.compile
import sbt.addCompilerPlugin

name := "assignment09"

version := "0.1"

scalaVersion in ThisBuild := "2.12.8"

addCompilerPlugin(scalafixSemanticdb)

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.14.0"

compile in Compile := (Def.taskDyn {
  val compilation = (compile in Compile).value
  Def.task {
    (scalafix in Compile).toTask("").value
    compilation
  }
}).value