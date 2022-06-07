import sbt._

object Dependencies {
  val ZHTTPVersion = "2.0.0-RC7"
  val ZioVersion = "2.0.0-RC5"
  val zConfigVersion = "3.0.0-RC8"
  val zioJsonVersion = "0.3.0-RC7"
  val zioLogVersion = "0.5.14"
  val slf4jVersion = "1.7.36"
  val jedisVersion = "4.2.2"

  lazy val zConfig: Seq[ModuleID] = Seq(
    "dev.zio" %% "zio-config" % zConfigVersion,
    "dev.zio" %% "zio-config-magnolia" % zConfigVersion,
    "dev.zio" %% "zio-config-typesafe" % zConfigVersion
  )

  lazy val zHttp: Seq[ModuleID] = Seq(
    "io.d11" %% "zhttp" % ZHTTPVersion,
    "io.d11" %% "zhttp-test" % ZHTTPVersion % Test
  )

  lazy val authentication: Seq[ModuleID] = Seq(
    "com.github.jwt-scala" %% "jwt-core" % "9.0.5",
    "io.github.jmcardon" %% "tsec-password" % "0.4.0",
  )

  lazy val zio: Seq[ModuleID] = Seq (
    "dev.zio" %% "zio"          % ZioVersion,
    "dev.zio" %% "zio-interop-cats" % "3.3.0-RC4",
    "dev.zio" %% "zio-test"     % ZioVersion % Test,
    "dev.zio" %% "zio-test-sbt" % ZioVersion % Test
  )

  lazy val kitten: Seq[ModuleID] = Seq(
    "org.typelevel" %% "kittens" % "2.3.2"
  )

  lazy val zioJson: Seq[ModuleID] = Seq(
    "dev.zio" %% "zio-json" % zioJsonVersion,
    //"dev.zio" % "zio-json-interop-refined" % "can't find release"
    "eu.timepit" %% "refined" % "0.9.28"
  )

  lazy val zioLog: Seq[ModuleID] = Seq(
    "dev.zio" %% "zio-logging" % zioLogVersion,
    "dev.zio" %% "zio-logging-slf4j" % zioLogVersion,
    "org.slf4j" % "slf4j-api" % slf4jVersion,
    "org.slf4j" % "slf4j-simple" % slf4jVersion
  )

  lazy val jedis: Seq[ModuleID] = Seq(
    "redis.clients" % "jedis" % jedisVersion,
    "org.apache.commons" % "commons-pool2" % "2.11.1"
  )

  lazy val quill: Seq[ModuleID] = Seq(
    "io.getquill"          %% "quill-jdbc-zio" % "3.17.0-RC3",
    "io.github.kitlangton" %% "zio-magic"      % "0.3.12",
    "org.postgresql"       %  "postgresql"     % "42.3.4"
  )
}
