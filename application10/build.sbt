name := "application"

version := "1.0"

scalaVersion := "2.12.10"

val http4sVersion = "0.20.0"

val circeVersion = "0.11.1"

val doobieVersion = "0.8.4"

libraryDependencies ++= List(
  "io.monix" %% "monix" % "3.0.0",
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion,

  "io.circe" %% "circe-generic" % circeVersion,

  "org.tpolecat" %% "doobie-core" % doobieVersion,
  "org.tpolecat" %% "doobie-h2" % doobieVersion,

  "io.chrisdavenport" %% "log4cats-slf4j" % "1.0.0",
  "ch.qos.logback" % "logback-classic" % "1.2.3"
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
