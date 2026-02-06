ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.8.1"

lazy val root = (project in file("."))
  .settings(
    name := "scala-assignment"
  )

// Akka 2.8+ and Akka HTTP 10.5+ require the Akka Repository
resolvers += "Akka library repository".at("https://repo.akka.io/maven")

val akkaVersion = "2.8.8"
val akkaHttpVersion = "10.5.3" // Compatible with Akka 2.8
val scalaTestVersion = "3.2.19"

libraryDependencies ++= Seq(
  // Core Akka
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-stream"      % akkaVersion,
  "com.typesafe.akka" %% "akka-testkit"     % akkaVersion % Test,
  
  // Akka HTTP
  "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  
  // Logging & Testing
  "ch.qos.logback"    %  "logback-classic" % "1.2.3",
  "org.scalatest"     %% "scalatest"       % scalaTestVersion % Test
)