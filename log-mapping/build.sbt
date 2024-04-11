ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.3"

lazy val root = (project in file("."))
  .settings(
    name := "log-mapping",
    idePackagePrefix := Some("dev.ardvci")
  )

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", xs @ _*) => MergeStrategy.discard
  case x => MergeStrategy.first
}

libraryDependencies += "io.circe" %% "circe-core" % "0.14.6"
libraryDependencies += "io.circe" %% "circe-generic" % "0.14.6"
libraryDependencies += "io.circe" %% "circe-parser" % "0.14.6"
libraryDependencies += "io.circe" %% "circe-yaml-v12" % "1.15.0"
libraryDependencies += "com.github.nscala-time" %% "nscala-time" % "2.32.0"
libraryDependencies += "com.rabbitmq" % "amqp-client" % "5.21.0"