package com.irwingarry.vitalsigns.core

import java.time.Instant

import com.typesafe.scalalogging.LazyLogging

import scala.util.Random

object HeartbeatGenerator extends App with LazyLogging {

  val patientId = 1L
  val systolicInterval = 40 to 60
  val diastolicInterval = 400 to 600
  val random = new Random()
  val producer = new HeartbeatProducer()

  logger.info("Let's-a-go!")

  while (true) {
    val atrial = AtrialContraction(patientId, Instant.now)
    logger.info(s"Heartbeat: $atrial")
    producer.send(atrial)
    sleep(systolicInterval)

    val ventricular = VentricularContraction(patientId, Instant.now)
    logger.info(s"Heartbeat: $ventricular")
    producer.send(ventricular)
    sleep(diastolicInterval)
  }

  private def sleep(interval: Range): Unit = Thread.sleep(
    interval.start + random.nextInt(interval.end - interval.start + 1)
  )
}
