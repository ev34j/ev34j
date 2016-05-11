package com.ev34j.core.motor;

/**
 * Abstraction for a  Lego NXT motor.
 */
public class RegulatedMotor
    extends BaseRegulatedMotor {

  public RegulatedMotor(final MotorPort motorPort) {
    super(RegulatedMotor.class, motorPort);
  }
}
