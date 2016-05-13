package com.ev34j.mindstorm.motor;

import com.ev34j.core.motor.LargeRegulatedMotor;
import com.ev34j.core.motor.MotorPort;
import com.ev34j.core.utils.Delay;

public abstract class AbstractMultiMotors {

  private final LargeRegulatedMotor motor1;
  private final LargeRegulatedMotor motor2;

  protected AbstractMultiMotors(final String portName1, final String portName2) {
    this.motor1 = new LargeRegulatedMotor(MotorPort.findByPort(portName1));
    this.motor2 = new LargeRegulatedMotor(MotorPort.findByPort(portName2));
  }

  protected LargeRegulatedMotor getMotor1() {
    return this.motor1;
  }

  protected LargeRegulatedMotor getMotor2() {
    return this.motor2;
  }

  public void off() {
    this.getMotor1().stop();
    this.getMotor2().stop();
  }

  public void reset() {
    this.getMotor1().reset();
    this.getMotor2().reset();
  }

  public void waitUntilStopped() {
    // Pause to give motors a chance to start
    Delay.millis(AbstractMotor.getMotorPollingWaitMillis());
    while (this.motor1.isMoving() || this.motor2.isMoving())
      Delay.millis(AbstractMotor.getMotorPollingWaitMillis());
  }
}
