package com.irwingarry.vitalsigns.producers

import com.irwingarry.vitalsigns.core._
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
    sendAndSleep(Atrial(patientId), systolicInterval)
    sendAndSleep(Ventricular(patientId), diastolicInterval)
  }

  private def sendAndSleep(contraction: Contraction, interval: Range): Unit = {
    logger.info(s"Heartbeat: $contraction")
    producer.send(contraction)
    Thread.sleep(random.between(interval.start, interval.end))
  }
}
