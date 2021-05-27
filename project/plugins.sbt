/*
Example of a Git based plugin

//lazy val root = (project in file(".")).dependsOn(tsiPlugin)

//lazy val tsiPlugin =
RootProject(uri("https://github.com/Helio-Health-Group/scala-tsi.git#68edb6cd824e01b3fb39388b0bd16f969cce8631"))
 */

libraryDependencies += "org.postgresql" % "postgresql" % "42.2.19"

addSbtPlugin("org.scalikejdbc" %% "scalikejdbc-mapper-generator" % "3.4.1")
