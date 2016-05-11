package com.ev34j.mindstorm.motor;

import com.ev34j.core.motor.MediumRegulatedMotor;
import com.ev34j.core.motor.MotorPort;

public class MediumMotor
    extends AbstractMotor {

  public MediumMotor(final String portName) {
    super(new MediumRegulatedMotor(MotorPort.findByPort(portName)));
  }
}
