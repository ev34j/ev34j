package com.ev34j.mindstorms.motor;

import com.ev34j.core.motor.BaseRegulatedMotor;
import com.ev34j.core.utils.Delay;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;

public abstract class AbstractMotor {

  private static final AtomicInteger MOTOR_POLL_WAIT_MILLIS = new AtomicInteger(100);

  static void validateSteering(final int steering) {
    if (steering < -100 || steering > 100)
      throw new IllegalArgumentException(format("Invalid steering value: %d. Valid values are -100 to 100.", steering));
  }

  static void validatePower(final int percentPower) {
    if (percentPower < -100 || percentPower > 100)
      throw new IllegalArgumentException(format("Invalid percent power value: %d. Valid values are -100 to 100.", percentPower));
  }

  static void validateDegrees(final int degrees) {
    if (degrees < 0)
      throw new IllegalArgumentException(format("Invalid degrees value: %d. Valid values are non-negative.", degrees));
  }

  static void validateRotations(final double rotations) {
    if (rotations < 0)
      throw new IllegalArgumentException(format("Invalid rotations value: %s. Valid values are non-negative.", rotations));
  }

  static void validateSeconds(final int secs) {
    if (secs < 0)
      throw new IllegalArgumentException(format("Invalid seconds value: %d. Valid values are non-negative.", secs));
  }

  static int getMotorPollingWaitMillis() { return MOTOR_POLL_WAIT_MILLIS.get(); }

  public static void setMotorPollingWaitMillis(final int waitMillis) { MOTOR_POLL_WAIT_MILLIS.set(waitMillis); }

  private final BaseRegulatedMotor motor;

  protected AbstractMotor(final BaseRegulatedMotor motor) {
    this.motor = motor;
    this.reset();
  }

  private void setPower(final int percentPower) {
    final int speed = (int) ((percentPower / 100F) * this.motor.getMaxSpeed());
    this.motor.setSpeed(speed);
  }

  private void advanceBy(final int position, final int percentPower) {
    this.setPower(percentPower);

    // Polarity is set only with advanceBy() calls
    this.motor.setForwardPolarity(percentPower > 0);

    this.motor.advanceBy(position);
  }

  private void advanceTo(final int position, final int percentPower) {
    this.setPower(percentPower);
    this.motor.advanceTo(position);
  }

  public int getPosition() { return (int) this.motor.getPosition(); }

  public int getPower() { return (this.motor.getSpeed() * 100) / this.motor.getMaxSpeed(); }

  public void reset() { this.motor.reset(); }

  public boolean isRunning() { return this.motor.isMoving(); }

  public boolean isStalled() { return this.motor.isStalled(); }

  public void enableCoastStop() { this.motor.setCoastStop(); }

  public boolean isCoastStopEnabled() { return this.motor.isCoastStop(); }

  public void enableBrakeStop() { this.motor.setBrakeStop(); }

  public boolean isBrakeStopEnabled() { return this.motor.isBrakeStop(); }

  public void enableHoldStop() { this.motor.setHoldStop(); }

  public boolean isHoldStopEnabled() { return this.motor.isHoldStop(); }

  /* Keep this hidden for now */
  private void setVariablePower(final int percentPower) {
    validatePower(percentPower);
    final int speed = (int) ((percentPower / 100F) * this.motor.getMaxSpeed());
    this.motor.setVariableSpeed(speed);
  }

  /* Keep this hidden for now */
  private void variableOn(final int initialPercentPower) {
    final int speed = (int) ((initialPercentPower / 100F) * this.motor.getMaxSpeed());
    this.setVariablePower(speed);
    this.motor.runVariable();
  }

  public void on(final int percentPower) {
    this.setPower(percentPower);
    this.motor.runForever();
  }

  public void off() {
    this.setPower(0);
    this.motor.stop();
  }

  public AbstractMotor onForSecs(final int secs, final int percentPower) {
    validateSeconds(secs);
    validatePower(percentPower);

    this.setPower(percentPower);
    this.motor.runForSecs(secs);
    return this;
  }

  public AbstractMotor onForDegrees(final int degrees, final int percentPower) {
    validateDegrees(degrees);
    validatePower(percentPower);

    this.advanceBy((int) ((degrees / 360F) * this.motor.getCountPerRotation()), percentPower);
    return this;
  }

  public AbstractMotor onForRotations(final double rotations, final int percentPower) {
    validateRotations(rotations);
    validatePower(percentPower);

    this.advanceBy((int) (this.motor.getCountPerRotation() * rotations), percentPower);
    return this;
  }

  public void waitUntilStopped() {
    // Pause to give motors a chance to start
    Delay.millis(getMotorPollingWaitMillis());
    while (this.motor.isMoving())
      Delay.millis(getMotorPollingWaitMillis());
  }
}

