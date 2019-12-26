scalaVersion     := "2.12.10"

version          := "0.1.0-SNAPSHOT"

organization     := "fintech.lecture10"

organizationName := "fintech"

name             := "lecture10"

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

val circeVersion = "0.12.2"

val doobieVersion = "0.8.4"

val http4sVersion = "0.20.0"

libraryDependencies ++= List(
  "io.monix" %% "monix" % "3.0.0",

  "io.circe" %% "circe-core" % circeVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "io.circe" %% "circe-parser" % circeVersion,

  "org.tpolecat" %% "doobie-core" % doobieVersion,
  "com.h2database" % "h2" % "1.4.197",
  "org.tpolecat" %% "doobie-h2" % doobieVersion,

  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion
)

addCompilerPlugin(scalafixSemanticdb)
