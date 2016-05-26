package com.ev34j.mindstorms.motor;

import com.ev34j.core.motor.LargeRegulatedMotor;
import com.ev34j.core.motor.MotorPort;
import com.ev34j.core.utils.Delay;

public abstract class AbstractMultiMotors {

  private final LargeRegulatedMotor motor1;
  private final LargeRegulatedMotor motor2;

  protected AbstractMultiMotors(final String portName1, final String portName2) {
    this.motor1 = new LargeRegulatedMotor(MotorPort.findByPort(portName1));
    this.motor2 = new LargeRegulatedMotor(MotorPort.findByPort(portName2));
    this.reset();
  }

  protected LargeRegulatedMotor getMotor1() { return this.motor1; }

  protected LargeRegulatedMotor getMotor2() { return this.motor2; }

  public void off() {
    this.motor1.setSpeed(0);
    this.motor2.setSpeed(0);
    this.motor1.stop();
    this.motor2.stop();
  }

  public void reset() {
    this.motor1.reset();
    this.motor2.reset();
  }

  public int getPower1() { return (this.motor1.getSpeed() * 100) / this.motor1.getMaxSpeed(); }

  public int getPower2() { return (this.motor2.getSpeed() * 100) / this.motor2.getMaxSpeed(); }

  public int getPosition1() { return (int) this.motor1.getPosition(); }

  public int getPosition2() { return (int) this.motor2.getPosition(); }

  public boolean isRunning() { return this.motor1.isMoving() || this.motor2.isMoving(); }

  public boolean isStalled() { return this.motor1.isStalled() || this.motor2.isStalled(); }

  public boolean isCoastStopEnabled() { return this.motor1.isCoastStop() || this.motor2.isCoastStop(); }

  public void enableCoastStop() {
    this.motor1.setCoastStop();
    this.motor2.setCoastStop();
  }

  public boolean isBrakeStopEnabled() { return this.motor1.isBrakeStop() || this.motor2.isBrakeStop(); }

  public void enableBrakeStop() {
    this.motor1.setBrakeStop();
    this.motor2.setBrakeStop();
  }

  public boolean isHoldStopEnabled() { return this.motor1.isHoldStop() || this.motor2.isHoldStop(); }

  public void enableHoldStop() {
    this.motor1.setHoldStop();
    this.motor2.setHoldStop();
  }

  public void waitUntilStopped() {
    // Pause to give motors a chance to start
    Delay.millis(AbstractMotor.getMotorPollingWaitMillis());
    while (this.motor1.isMoving() || this.motor2.isMoving())
      Delay.millis(AbstractMotor.getMotorPollingWaitMillis());
  }
}
