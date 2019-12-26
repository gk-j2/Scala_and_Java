scalaVersion     := "2.12.10"

version          := "0.1.0-SNAPSHOT"

organization     := "fintech.lecture02"

organizationName := "fintech"

name             := "assignment02"

lazy val root = (project in file("."))
  .enablePlugins(RunAcceptance)
  .settings(
    inConfig(AcceptanceTest)(
      Seq(acceptanceTestsPath := "fintech/assignment02")
    )
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
