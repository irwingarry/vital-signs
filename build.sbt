lazy val root = project
  .in(file("."))
  .aggregate(core, producers)
  .settings(baseSettings, name := "vital-signs-root")

lazy val core = project.settings(
  baseSettings,
  name := "vital-signs-core",
  libraryDependencies ++= coreDependencies
)

lazy val producers = project
  .dependsOn(core)
  .settings(
    baseSettings,
    name := "vital-signs-producers",
    libraryDependencies ++= producersDependencies
  )

lazy val baseSettings = Seq(scalaVersion := "2.13.1", version := "0.1")

lazy val coreDependencies = Seq(
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
)

lazy val producersDependencies = Seq(
  "org.apache.kafka" % "kafka-clients" % "2.4.0"
)
