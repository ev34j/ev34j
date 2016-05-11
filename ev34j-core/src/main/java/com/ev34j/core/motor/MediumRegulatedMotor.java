package com.ev34j.core.motor;

/**
 * Abstraction for a Medium Lego EV3/NXT motor.
 */
public class MediumRegulatedMotor
    extends BaseRegulatedMotor {

  public MediumRegulatedMotor(final MotorPort motorPort) {
    super(MediumRegulatedMotor.class, motorPort);
  }
}
