package com.ev34j.core.motor;

/**
 * Abstraction for a Large Lego EV3/NXT motor.
 */
public class LargeRegulatedMotor
    extends BaseRegulatedMotor {

  public LargeRegulatedMotor(final MotorPort motorPort) {
    super(LargeRegulatedMotor.class, motorPort);
  }
}
