scalaVersion     := "2.12.10"

version := "0.1.0-SNAPSHOT"

organization := "fintech.lecture08"

organizationName := "fintech"

name := "lecture08"

libraryDependencies ++= List(
  "com.h2database" % "h2" % "1.4.197",
  "org.scalacheck" %% "scalacheck" % "1.14.2" % Test
)

scalacOptions ++= List(
  "-deprecation",
  "-feature",
  "-Ypartial-unification",
  "-Yrangepos",
  "-Ywarn-unused:implicits",
  "-Ywarn-unused:imports",
  "-Ywarn-unused:locals",
  "-Ywarn-unused:params",
  "-Ywarn-unused:patvars",
  "-Ywarn-unused:privates",
  "-Ywarn-adapted-args"
)

addCompilerPlugin(scalafixSemanticdb)

compile in Compile := (Def.taskDyn {
  val compilation = (compile in Compile).value
  Def.task {
    (scalafix in Compile).toTask("").value
    compilation
  }
}).value
