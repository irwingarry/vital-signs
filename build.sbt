name := "vital-signs"
version := "0.1"
scalaVersion := "2.12.10"
libraryDependencies ++= producersDependencies ++ coreDependencies ++ flinkDependencies

lazy val coreDependencies = Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "com.fasterxml.jackson.module" %% "jackson-module-scala" % "2.10.1",
  "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % "2.10.1"
)

lazy val producersDependencies = Seq(
  "org.apache.kafka" % "kafka-clients" % "2.4.0"
)

val flinkVersion = "1.9.1"
lazy val flinkDependencies = Seq(
  "org.apache.flink" %% "flink-scala" % flinkVersion,
  "org.apache.flink" %% "flink-streaming-scala" % flinkVersion,
  "org.apache.flink" %% "flink-connector-kafka" % flinkVersion
)
