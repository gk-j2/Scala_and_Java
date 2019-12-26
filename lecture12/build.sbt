scalaVersion := "2.12.10"

version := "0.1.0-SNAPSHOT"

organization := "fintech.lecture12"

organizationName := "fintech"

name := "lecture12"

scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-Ywarn-unused:implicits",
  "-Ywarn-unused:imports",
  "-Ywarn-unused:locals",
  "-Ywarn-unused:params",
  "-Ywarn-unused:patvars",
  "-Ywarn-unused:privates"
)
