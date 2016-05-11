package com.ev34j.mindstorm.motor;

import static com.ev34j.mindstorm.motor.AbstractMotor.validateDegrees;
import static com.ev34j.mindstorm.motor.AbstractMotor.validatePower;
import static com.ev34j.mindstorm.motor.AbstractMotor.validateRotations;
import static com.ev34j.mindstorm.motor.AbstractMotor.validateSeconds;
import static com.ev34j.mindstorm.motor.AbstractMotor.validateSteering;

public class SteeringMotors
    extends AbstractMultiMotors {

  public SteeringMotors(final String portName1, final String portName2) {
    super(portName1, portName2);
  }

  private void setPower(final int steering, final int percentPower) {
    final float adjust1 = steering < 0 ? (100 - Math.abs(steering)) / 100F : 1F;
    final float adjust2 = steering > 0 ? (100 - steering) / 100F : 1F;

    final int speed1 = (int) ((percentPower / 100F) * this.getMotor1().getMaxSpeed() * adjust1);
    final int speed2 = (int) ((percentPower / 100F) * this.getMotor2().getMaxSpeed() * adjust2);

    this.getMotor1().setSpeed(speed1);
    this.getMotor2().setSpeed(speed2);
  }

  private void advanceBy(final int position, final int steering, final int percentPower) {
    if (position == 0)
      return;

    final float adjust1 = steering < 0 ? (100 - Math.abs(steering)) / 100F : 1F;
    final float adjust2 = steering > 0 ? (100 - steering) / 100F : 1F;

    final int position1 = (int) (position * adjust1);
    final int position2 = (int) (position * adjust2);

    this.setPower(steering, percentPower);
    this.getMotor1().advanceBy(position1);
    this.getMotor2().advanceBy(position2);
  }

  private void setVariablePower(final int steering, final int percentPower) {
    final float adjust1 = steering < 0 ? (100 - Math.abs(steering)) / 100F : 1F;
    final float adjust2 = steering > 0 ? (100 - steering) / 100F : 1F;

    final int speed1 = (int) ((percentPower / 100F) * this.getMotor1().getMaxSpeed() * adjust1);
    final int speed2 = (int) ((percentPower / 100F) * this.getMotor2().getMaxSpeed() * adjust2);

    this.getMotor1().setVariableSpeed(speed1);
    this.getMotor2().setVariableSpeed(speed2);
  }

  private SteeringMotors variableOn(final int steering, final int initialPercentPower) {
    this.setVariablePower(steering, initialPercentPower);

    this.getMotor1().runVariable();
    this.getMotor2().runVariable();
    return this;
  }

  public void on(final int steering, final int percentPower) {
    validateSteering(steering);
    validatePower(percentPower);

    this.setPower(steering, percentPower);
    this.getMotor1().runForever();
    this.getMotor2().runForever();
  }

  public SteeringMotors onForSecs(final int secs, final int steering, final int percentPower) {
    validateSeconds(secs);
    validateSteering(steering);
    validatePower(percentPower);

    this.setPower(steering, percentPower);
    this.getMotor1().runForSecs(secs);
    this.getMotor2().runForSecs(secs);
    return this;
  }

  public SteeringMotors onForDegrees(final int degrees, final int steering, final int percentPower) {
    validateDegrees(degrees);
    validateSteering(steering);
    validatePower(percentPower);

    final int position = (int) ((degrees / 360F) * this.getMotor1().getCountPerRotation());
    this.advanceBy(position, steering, percentPower);
    return this;
  }

  public SteeringMotors onForRotations(final float rotations, final int steering, final int percentPower) {
    validateRotations(rotations);
    validateSteering(steering);
    validatePower(percentPower);

    final int position = (int) (this.getMotor1().getCountPerRotation() * rotations);
    this.advanceBy(position, steering, percentPower);
    return this;
  }
}
