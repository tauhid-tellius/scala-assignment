ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.8.1"

lazy val root = (project in file("."))
  .settings(
    name := "scala-assignment"
  )

val akkaVersion = "2.10.11"
val scalaTestVersion = "3.2.19"
val AkkaHttpVersion = "10.7.3"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit" % akkaVersion,
  "org.scalatest" %% "scalatest" % scalaTestVersion % Test,
  "ch.qos.logback" % "logback-classic" % "1.2.3",

  // akka-http
  "com.typesafe.akka" %% "akka-actor-typed" % AkkaVersion,
  "com.typesafe.akka" %% "akka-stream" % AkkaVersion,
  "com.typesafe.akka" %% "akka-http" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion
)