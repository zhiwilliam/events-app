import Dependencies._

lazy val util = project
lazy val core = project.dependsOn(util)
lazy val app = project.dependsOn(core).settings(
    libraryDependencies ++= zio ++ zHttp ++ zioJson ++ authentication
  )

ThisBuild / scalaVersion := "2.13.8"
lazy val compilecheck = taskKey[Unit]("compile and then scalastyle")

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .aggregate(util, core, app)
  .settings(update / aggregate := false)
  .settings(BuildHelper.stdSettings)
  .settings(
    name := "wzhi",
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"),
    libraryDependencies ++= authentication ++ zio ++ zHttp ++ kitten ++ zConfig ++ zioJson ++ zioLog ++ jedis ++ quill,
  )
  .settings(
    Docker / version          := version.value,
    Compile / run / mainClass := Option("WebServer"),
  )

addCommandAlias("fmt", "scalafmt; Test / scalafmt; sFix;")
addCommandAlias("fmtCheck", "scalafmtCheck; Test / scalafmtCheck; sFixCheck")
addCommandAlias("sFix", "scalafix OrganizeImports; Test / scalafix OrganizeImports")
addCommandAlias("sFixCheck", "scalafix --check OrganizeImports; Test / scalafix --check OrganizeImports")