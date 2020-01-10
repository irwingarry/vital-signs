package com.irwingarry.vitalsigns

import java.time.Instant

package object core {
  sealed abstract class Contraction(patientId: Long, timestamp: Instant)
  case class Atrial(patientId: Long) extends Contraction(patientId, Instant.now)
  case class Ventricular(patientId: Long)
      extends Contraction(patientId, Instant.now)
}
