name := "helio-http4s"

version := "0.1"

scalaVersion := "2.13.6"

enablePlugins(ScalikejdbcPlugin)

val logbackVersion = "1.2.3"
val circeVersion = "0.13.0"
val http4sVersion = "0.21.22"
val scalikejdbcVersion = "3.4.1"
val specs2Version = "4.12.0"

libraryDependencies ++= Seq(
  "ch.qos.logback" % "logback-classic" % logbackVersion,
  "io.circe" %% "circe-generic" % circeVersion,
  "org.http4s" %% "http4s-dsl" % http4sVersion,
  "org.http4s" %% "http4s-circe" % http4sVersion,
  "org.http4s" %% "http4s-blaze-server" % http4sVersion,
  "org.http4s" %% "http4s-blaze-client" % http4sVersion,
  "org.scalikejdbc" %% "scalikejdbc" % scalikejdbcVersion,
  "com.h2database" % "h2" % "1.4.199",
  "org.scalikejdbc" %% "scalikejdbc-test" % scalikejdbcVersion % "test",
  "org.specs2" %% "specs2-core" % specs2Version % "test",
  "org.postgresql" % "postgresql" % "42.2.19"
)
