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

  public int getPower1() { return ((this.getMotor1().getSpeed() * 100) / this.getMotor1().getMaxSpeed()); }

  public int getPower2() { return ((this.getMotor2().getSpeed() * 100) / this.getMotor2().getMaxSpeed()); }

  public int getPosition1() { return (int) this.getMotor1().getPosition(); }

  public int getPosition2() { return (int) this.getMotor2().getPosition(); }

  public boolean isRunning() { return this.getMotor1().isMoving() || this.getMotor2().isMoving(); }

  public boolean isStalled() { return this.getMotor1().isStalled() || this.getMotor2().isStalled(); }

  public boolean isCoastStopEnabled() { return this.getMotor1().isCoastStop() || this.getMotor2().isCoastStop(); }

  public void enableCoastStop() {
    this.getMotor1().setCoastStop();
    this.getMotor2().setCoastStop();
  }

  public boolean isBrakeStopEnabled() { return this.getMotor1().isBrakeStop() || this.getMotor2().isBrakeStop(); }

  public void enableBrakeStop() {
    this.getMotor1().setBrakeStop();
    this.getMotor2().setBrakeStop();
  }

  public boolean isHoldStopEnabled() { return this.getMotor1().isHoldStop() || this.getMotor2().isHoldStop(); }

  public void enableHoldStop() {
    this.getMotor1().setHoldStop();
    this.getMotor2().setHoldStop();
  }

  public void waitUntilStopped() {
    // Pause to give motors a chance to start
    Delay.millis(AbstractMotor.getMotorPollingWaitMillis());
    while (this.getMotor1().isMoving() || this.getMotor2().isMoving())
      Delay.millis(AbstractMotor.getMotorPollingWaitMillis());
  }
}
