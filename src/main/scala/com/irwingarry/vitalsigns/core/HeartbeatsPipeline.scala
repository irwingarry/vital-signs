package com.irwingarry.vitalsigns.core

import java.util.Properties

import com.typesafe.scalalogging.LazyLogging
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer

object HeartbeatsPipeline extends App with LazyLogging {

  val kafkaConsumer = {
    val properties = new Properties
    properties.setProperty("bootstrap.servers", "localhost:9092")
    properties.setProperty("zookeeper.connect", "localhost:2181")
    properties.setProperty("group.id", "1")
    properties.setProperty("auto.offset.reset", "latest")
    properties.setProperty("enable.auto.commit", "true")
    new FlinkKafkaConsumer[Contraction](
      topic,
      new HeartbeatDeserializer,
      properties
    )
  }

  val env = StreamExecutionEnvironment.getExecutionEnvironment()
  env.addSource(kafkaConsumer).setParallelism(1).print("Heartbeat Sink")
  env.execute()

  logger.info("finito")
}
