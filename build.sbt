import Dependencies._

lazy val util = project
  .settings(publish / skip := true, assemblyOpt)
  
lazy val core = project
  .dependsOn(util)
  .settings(publish / skip := true, assemblyOpt)
  
lazy val app = project.dependsOn(core).settings(
    libraryDependencies ++= zio ++ zHttp ++ zioJson ++ authentication
  )
  .settings(publish / skip := true, assemblyOpt)
  .settings(
    Docker / version          := version.value,
    Compile / run / mainClass := Option("WebServer"),
  )

ThisBuild / scalaVersion := "2.13.8"
lazy val compilecheck = taskKey[Unit]("compile and then scalastyle")

lazy val root = (project in file("."))
  .aggregate(util, core, app)
  .settings(update / aggregate := false)
  .settings(BuildHelper.stdSettings, assemblyOpt)
  .settings(
    name := "wzhi",
    Compile / mainClass := Some("com.wzhi.Main"),
    testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework"),
    libraryDependencies ++= authentication ++ zio ++ zHttp ++ kitten ++ zConfig ++ zioJson ++ zioLog ++ jedis ++ quill
  )
  .settings(
    Docker / version          := version.value,
    Compile / run / mainClass := Option("WebServer"),
  )
  .enablePlugins(JavaAppPackaging)
  .enablePlugins(DockerPlugin)

lazy val assemblyOpt = Seq(
  assembly / assemblyMergeStrategy := {
    case PathList("META-INF", "MANIFEST.MF") => MergeStrategy.discard
    case "META-INF/io.netty.versions.properties" => MergeStrategy.first
    //case PathList("META-INF", "io.netty.versions.properties", xs @ _*) => MergeStrategy.discard
    case x => {
    val oldStrategy = (assembly / assemblyMergeStrategy).value
    val strategy = oldStrategy(x)
    if (strategy == MergeStrategy.deduplicate)
        MergeStrategy.first
    else strategy
    }
  }
)
// Remove all jar mappings in universal and append the fat jar
Universal / mappings := {
  val universalMappings = (Universal / mappings).value
  val fatJar = (Compile / assembly).value
  val filtered = universalMappings.filter {
    case (file, name) => !name.endsWith(".jar")
  }
  filtered :+ (fatJar -> ("lib/" + fatJar.getName))
}

addCommandAlias("fmt", "scalafmt; Test / scalafmt; sFix;")
addCommandAlias("fmtCheck", "scalafmtCheck; Test / scalafmtCheck; sFixCheck")
addCommandAlias("sFix", "scalafix OrganizeImports; Test / scalafix OrganizeImports")
addCommandAlias("sFixCheck", "scalafix --check OrganizeImports; Test / scalafix --check OrganizeImports")