package com.ev34j.mindstorms.motor;

import com.ev34j.core.motor.LargeRegulatedMotor;
import com.ev34j.core.motor.MotorPort;

public class LargeMotor
    extends AbstractMotor {

  public LargeMotor(final String portName) {
    super(new LargeRegulatedMotor(MotorPort.findByPort(portName)));
  }
}
