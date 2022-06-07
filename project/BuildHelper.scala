import sbt.Keys._
import sbt.{Def, _}

object BuildHelper {
  val ScalaVersion = "2.13.8"

  def commonSettings(scalaVersion: String): Seq[String] = CrossVersion.partialVersion(scalaVersion) match {
    case Some((3, _))                  => Seq.empty
    case Some((2, 12)) | Some((2, 13)) => Seq("-Ywarn-unused:params")
    case _                             => Seq.empty
  }
  def stdSettings: Seq[Def.Setting[_ >: Boolean with String with Task[Seq[String]]]] = Seq(
    ThisBuild / fork              := true,
    ThisBuild / scalaVersion      := ScalaVersion,
    ThisBuild / scalacOptions     := commonSettings(scalaVersion.value),
    ThisBuild / semanticdbEnabled := false,
  )
}
