package com.ev34j.core.motor;


import com.ev34j.core.common.AttributeValue;
import com.ev34j.core.common.PlatformType;

import java.util.Collections;
import java.util.List;

import static com.ev34j.core.common.AttributeName.MODE;
import static com.ev34j.core.common.AttributeName.POWER;
import static com.ev34j.core.common.AttributeName.STATE;
import static com.ev34j.core.common.AttributeName.VARIABLE_SPEED_VAL;
import static com.ev34j.core.common.AttributeValue.BRAKE;
import static com.ev34j.core.common.AttributeValue.RUNNING_STATE;
import static com.ev34j.core.common.AttributeValue.RUN_FOREVER;
import static com.ev34j.core.common.AttributeValue.STOP;
import static com.ev34j.core.common.PlatformType.EV3BRICK;
import static com.ev34j.core.motor.PortType.DC_MOTOR;
import static com.ev34j.core.motor.PortType.LEGO_PORT;

/**
 * Abstraction for an Lego Mindstorms motors with no speed regulation.
 * http://www.ev3dev.org/docs/motors/
 */
public class BaseUnregulatedMotor
    extends GenericMotor { //implements DCMotor

  // This feature is only allowed with EV3Brick
  private final static List<PlatformType> SUPPORTED_PLATFORMS = Collections.singletonList(EV3BRICK);

  private int power = 0;

  public BaseUnregulatedMotor(final MotorPort motorPort) {
    super(BaseUnregulatedMotor.class, LEGO_PORT, motorPort, SUPPORTED_PLATFORMS);
    this.setAttribute(MODE, AttributeValue.DC_MOTOR);

    this.detectDevice(this.getClass(), DC_MOTOR, motorPort.getPortName(), motorPort.getPortAddress());
  }

  public void setPower(int power) {
    this.power = power;
    this.setAttribute(VARIABLE_SPEED_VAL, this.power);
  }

  public int getPower() { return this.getIntegerAttribute(POWER); }

  /**
   * Causes motor to rotate forward.
   */
  public void forward() {
    if (this.power != 0) {
      int value = Math.abs(this.power);
      this.setAttribute(VARIABLE_SPEED_VAL, value);
    }
    this.command(RUN_FOREVER);
  }

  /**
   * Causes motor to rotate backwards.
   */
  public void backward() {
    if (this.power != 0) {
      int value = Math.abs(this.power) * -1;
      this.setAttribute(VARIABLE_SPEED_VAL, value);
    }
    //TODO: Learn to use this attribute
    //final String attribute1 = "inversed";
    //final String value1 = "polarity";
    this.command(RUN_FOREVER);
  }

  /**
   * Returns true iff the motor is in motion.
   *
   * @return true iff the motor is currently in motion.
   */
  public boolean isMoving() { return (this.getStringAttribute(STATE).contains(RUNNING_STATE.attribValue())); }

  /**
   * Causes motor to float. The motor will lose all power,
   * but this is not the same as stopping. Use this
   * method if you don't want your robot to trip in
   * abrupt turns.
   */
  public void flt() { this.command(BRAKE); }

  /**
   * Causes motor to stop, pretty much
   * instantaneously. In other words, the
   * motor doesn't just stop; it will resist
   * any further motion.
   * Cancels any rotate() orders in progress
   */
  public void stop() { this.command(STOP); }
}
