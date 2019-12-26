name := "assignment01"

version := "0.1"

scalaVersion := "2.12.8"

lazy val root = (project in file("."))
  .enablePlugins(RunAcceptance)
  .settings(
    inConfig(AcceptanceTest)(
      Seq(acceptanceTestsPath := "exercises")
    )
  )
