name := "helio-http4s"

version := "0.1"

scalaVersion := "2.13.6"

val circeVersion = "0.13.0"
val http4sVersion = "0.21.22"

libraryDependencies ++= Seq(
  "io.circe" %% "circe-generic" % circeVersion,
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion
)

scalacOptions ++= Seq("-Ywarn-unused:imports")
