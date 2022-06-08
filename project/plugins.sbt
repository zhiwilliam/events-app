addSbtPlugin("io.spray"       % "sbt-revolver"        % "0.9.1")
addSbtPlugin("ch.epfl.scala"  % "sbt-scalafix"        % "0.9.34")
addSbtPlugin("org.scalameta"  % "sbt-scalafmt"        % "2.4.6")
addSbtPlugin("com.github.sbt" % "sbt-native-packager" % "1.9.9")
addSbtPlugin("com.eed3si9n"   % "sbt-assembly"        % "0.15.0")
libraryDependencies += "com.spotify" % "docker-client" % "8.9.0"