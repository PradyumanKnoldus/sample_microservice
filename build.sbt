ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "sample_microservice",
    idePackagePrefix := Some("com.nashtechglobal")
  )

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.8.0",
  "com.typesafe.akka" %% "akka-stream" % "2.8.0",
  "com.typesafe.akka" %% "akka-http" % "10.5.0",
  "org.apache.kafka" % "kafka-clients" % "3.4.0",
  "io.circe" %% "circe-core" % "0.14.5",
  "io.circe" %% "circe-generic" % "0.14.5",
  "io.circe" %% "circe-parser" % "0.14.5",
  "com.typesafe.akka" %% "akka-http-spray-json" % "10.5.0",
  "io.spray" %% "spray-json" % "1.3.6",
  "ch.qos.logback" % "logback-classic" % "1.4.7",
  "redis.clients" % "jedis" % "4.4.0"
)