package com.irwingarry.vitalsigns

import java.time.Instant

import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule

package object core {

  val topic = "vital-signs-heartbeat-topic"
  val key = "vital-signs-heartbeat-key"

  val objectMapper =
    new ObjectMapper()
      .registerModule(DefaultScalaModule)
      .registerModule(new JavaTimeModule())

  @JsonTypeInfo(
    use = JsonTypeInfo.Id.CLASS,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@class"
  )
  sealed abstract class Contraction(patientId: Long, timestamp: Instant)
  case class AtrialContraction(patientId: Long, timestamp: Instant)
      extends Contraction(patientId, timestamp)
  case class VentricularContraction(patientId: Long, timestamp: Instant)
      extends Contraction(patientId, timestamp)
}
