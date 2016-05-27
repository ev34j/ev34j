package com.ev34j.mindstorms.motor;

import static com.ev34j.mindstorms.motor.AbstractMotor.validateDegrees;
import static com.ev34j.mindstorms.motor.AbstractMotor.validatePower;
import static com.ev34j.mindstorms.motor.AbstractMotor.validateRotations;
import static com.ev34j.mindstorms.motor.AbstractMotor.validateSeconds;
import static com.ev34j.mindstorms.motor.AbstractMotor.validateSteering;

public class SteeringMotors
    extends AbstractMultiMotors {

  private int steering = 0;

  public SteeringMotors(final String portName1, final String portName2) {
    super(portName1, portName2);
  }

  public int getSteering() { return this.steering; }

  private void setPower(final int percentPower) {
    final float adjust1 = this.steering < 0 ? (100 - Math.abs(this.steering)) / 100F : 1F;
    final float adjust2 = this.steering > 0 ? (100 - this.steering) / 100F : 1F;

    final int speed1 = (int) ((percentPower / 100F) * this.getMotor1().getMaxSpeed() * adjust1);
    final int speed2 = (int) ((percentPower / 100F) * this.getMotor2().getMaxSpeed() * adjust2);

    this.getMotor1().setSpeed(speed1);
    this.getMotor2().setSpeed(speed2);
  }

  private void advanceBy(final int percentPower, final int position) {
    if (position == 0)
      return;

    final float adjust1 = this.steering < 0 ? (100 - Math.abs(this.steering)) / 100F : 1F;
    final float adjust2 = this.steering > 0 ? (100 - this.steering) / 100F : 1F;

    final int position1 = (int) (position * adjust1);
    final int position2 = (int) (position * adjust2);

    this.setPower(percentPower);

    // Polarity is set only with advanceBy() calls
    this.getMotor1().setForwardPolarity(percentPower > 0);
    this.getMotor2().setForwardPolarity(percentPower > 0);

    this.getMotor1().advanceBy(position1);
    this.getMotor2().advanceBy(position2);
  }

  private void setVariablePower(final int percentPower) {
    final float adjust1 = this.steering < 0 ? (100 - Math.abs(this.steering)) / 100F : 1F;
    final float adjust2 = this.steering > 0 ? (100 - this.steering) / 100F : 1F;

    final int speed1 = (int) ((percentPower / 100F) * this.getMotor1().getMaxSpeed() * adjust1);
    final int speed2 = (int) ((percentPower / 100F) * this.getMotor2().getMaxSpeed() * adjust2);

    this.getMotor1().setVariableSpeed(speed1);
    this.getMotor2().setVariableSpeed(speed2);
  }

  private SteeringMotors variableOn(final int steering, final int initialPercentPower) {
    validateSteering(steering);
    validatePower(initialPercentPower);

    this.steering = steering;
    this.setVariablePower(initialPercentPower);

    this.getMotor1().runVariable();
    this.getMotor2().runVariable();
    return this;
  }

  public void on(final int steering, final int percentPower) {
    validateSteering(steering);
    validatePower(percentPower);

    this.steering = steering;
    this.setPower(percentPower);
    this.getMotor1().runForever();
    this.getMotor2().runForever();
  }

  public SteeringMotors onForSecs(final int steering, final int percentPower, final int secs) {
    validateSteering(steering);
    validatePower(percentPower);
    validateSeconds(secs);

    this.steering = steering;
    this.setPower(percentPower);
    this.getMotor1().runForSecs(secs);
    this.getMotor2().runForSecs(secs);
    return this;
  }

  public SteeringMotors onForDegrees(final int steering, final int percentPower, final int degrees) {
    validateDegrees(degrees);
    return this.onForRotations(steering, percentPower, degrees / 360F);
  }

  public SteeringMotors onForRotations(final int steering, final int percentPower, final double rotations) {
    validateSteering(steering);
    validatePower(percentPower);
    validateRotations(rotations);

    this.steering = steering;
    final int position = (int) (this.getMotor1().getCountPerRotation() * rotations);
    this.advanceBy(percentPower, position);
    return this;
  }
}
