package com.ev34j.mindstorms.motor;

import static com.ev34j.mindstorms.motor.AbstractMotor.validateDegrees;
import static com.ev34j.mindstorms.motor.AbstractMotor.validatePower;
import static com.ev34j.mindstorms.motor.AbstractMotor.validateRotations;
import static com.ev34j.mindstorms.motor.AbstractMotor.validateSeconds;

public class TankMotors
    extends AbstractMultiMotors {

  public TankMotors(final String portName1, final String portName2) {
    super(portName1, portName2);
  }

  private void setPower(final int percentPower1, final int percentPower2) {
    final int speed1 = (int) ((percentPower1 / 100F) * this.getMotor1().getMaxSpeed());
    final int speed2 = (int) ((percentPower2 / 100F) * this.getMotor2().getMaxSpeed());

    this.getMotor1().setSpeed(speed1);
    this.getMotor2().setSpeed(speed2);
  }

  private void advanceBy(final int percentPower1, final int percentPower2, final int position) {
    if (position == 0)
      return;

    final int position1;
    final int position2;

    if (Math.abs(percentPower1) < Math.abs(percentPower2)) {
      position1 = (int) ((((Math.abs(percentPower2) - Math.abs(percentPower1)) * 1F) / Math.abs(percentPower2)) * position);
      position2 = position;
    }
    else if (Math.abs(percentPower1) > Math.abs(percentPower2)) {
      position1 = position;
      position2 = (int) ((((Math.abs(percentPower1) - Math.abs(percentPower2)) * 1F) / Math.abs(percentPower1)) * position);
    }
    else {
      position1 = position;
      position2 = position;
    }

    this.setPower(percentPower1, percentPower2);

    // Polarity is set only with advanceBy() calls
    this.getMotor1().setForwardPolarity(percentPower1 > 0);
    this.getMotor2().setForwardPolarity(percentPower2 > 0);

    this.getMotor1().advanceBy(position1);
    this.getMotor2().advanceBy(position2);
  }

  private void setVariablePower(final int percentPower1, final int percentPower2) {
    validatePower(percentPower1);
    validatePower(percentPower2);

    final int speed1 = (int) ((percentPower1 / 100F) * this.getMotor1().getMaxSpeed());
    final int speed2 = (int) ((percentPower2 / 100F) * this.getMotor1().getMaxSpeed());

    this.getMotor1().setVariableSpeed(speed1);
    this.getMotor2().setVariableSpeed(speed2);
  }

  private TankMotors variableOn(final int initialPercentPower1, final int initialPercentPower2) {
    validatePower(initialPercentPower1);
    validatePower(initialPercentPower2);

    this.setVariablePower(initialPercentPower1, initialPercentPower2);

    this.getMotor1().runVariable();
    this.getMotor2().runVariable();
    return this;
  }

  public void on(final int percentPower1, final int percentPower2) {
    validatePower(percentPower1);
    validatePower(percentPower2);

    this.setPower(percentPower1, percentPower2);
    this.getMotor1().runForever();
    this.getMotor2().runForever();
  }

  public TankMotors onForSecs(final int percentPower1, final int percentPower2, final int secs) {
    validateSeconds(secs);
    validatePower(percentPower1);
    validatePower(percentPower2);

    this.setPower(percentPower1, percentPower2);
    this.getMotor1().runForSecs(secs);
    this.getMotor2().runForSecs(secs);
    return this;
  }

  public TankMotors onForDegrees(final int percentPower1, final int percentPower2, final int degrees) {
    validateDegrees(degrees);
    validatePower(percentPower1);
    validatePower(percentPower2);

    this.advanceBy(percentPower1, percentPower2, (int) ((degrees / 360F) * this.getMotor1().getCountPerRotation()));
    return this;
  }

  public TankMotors onForRotations(final int percentPower1, final int percentPower2, final double rotations) {
    validateRotations(rotations);
    validatePower(percentPower1);
    validatePower(percentPower2);

    this.advanceBy(percentPower1, percentPower2, (int) (this.getMotor1().getCountPerRotation() * rotations));
    return this;
  }
}
