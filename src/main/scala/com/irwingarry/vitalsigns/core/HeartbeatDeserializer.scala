package com.irwingarry.vitalsigns.core

import org.apache.flink.api.common.serialization.AbstractDeserializationSchema

class HeartbeatDeserializer extends AbstractDeserializationSchema[Contraction] {

  override def deserialize(message: Array[Byte]): Contraction = {
    objectMapper.readValue(message, classOf[Contraction])
  }
}
