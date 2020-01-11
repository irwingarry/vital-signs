package com.irwingarry.vitalsigns.core

import java.util.Properties

import org.apache.kafka.clients.producer.{KafkaProducer, ProducerRecord}
import org.apache.kafka.common.serialization.StringSerializer

class HeartbeatProducer {

  val topic = "vital-signs-heartbeat-topic"
  val key = "vital-signs-heartbeat-key"

  lazy val producer = {
    val properties = new Properties()
    properties.put("bootstrap.servers", "localhost:9092")
    properties.put("zookeeper.connect", "2181")
    properties.put("broker.id", 1)
    properties.put("key.serializer", classOf[StringSerializer].getName)
    properties.put("value.serializer", classOf[StringSerializer].getName)
    new KafkaProducer[String, String](properties)
  }

  def send(contraction: Contraction) =
    producer.send(
      new ProducerRecord(
        topic,
        key,
        objectMapper.writeValueAsString(contraction)
      )
    )
}
