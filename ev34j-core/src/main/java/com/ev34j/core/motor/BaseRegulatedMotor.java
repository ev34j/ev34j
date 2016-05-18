package com.ev34j.core.motor;

import com.ev34j.core.common.AttributeName;
import com.ev34j.core.common.AttributeValue;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static com.ev34j.core.common.AttributeName.COUNT_PER_ROT;
import static com.ev34j.core.common.AttributeName.CURRENT_POSITION;
import static com.ev34j.core.common.AttributeName.FIXED_SPEED_VAL;
import static com.ev34j.core.common.AttributeName.MAX_SPEED;
import static com.ev34j.core.common.AttributeName.POLARITY;
import static com.ev34j.core.common.AttributeName.POSITION_VAL;
import static com.ev34j.core.common.AttributeName.STATE;
import static com.ev34j.core.common.AttributeName.STOP_ACTION;
import static com.ev34j.core.common.AttributeName.TIME_TO_RUN_VAL;
import static com.ev34j.core.common.AttributeName.VARIABLE_SPEED_VAL;
import static com.ev34j.core.common.AttributeValue.BRAKE;
import static com.ev34j.core.common.AttributeValue.COAST;
import static com.ev34j.core.common.AttributeValue.HOLD;
import static com.ev34j.core.common.AttributeValue.INVERSED_POLARITY;
import static com.ev34j.core.common.AttributeValue.NORMAL_POLARITY;
import static com.ev34j.core.common.AttributeValue.RESET;
import static com.ev34j.core.common.AttributeValue.RUNNING_STATE;
import static com.ev34j.core.common.AttributeValue.RUN_DIRECT;
import static com.ev34j.core.common.AttributeValue.RUN_FOREVER;
import static com.ev34j.core.common.AttributeValue.RUN_TIMED;
import static com.ev34j.core.common.AttributeValue.RUN_TO_ABS_POS;
import static com.ev34j.core.common.AttributeValue.RUN_TO_REL_POS;
import static com.ev34j.core.common.AttributeValue.STALLED_STATE;
import static com.ev34j.core.common.AttributeValue.STOP;
import static com.ev34j.core.motor.PortType.TACHO_MOTOR;
import static java.lang.String.format;

/**
 * Abstraction for a Regulated motor motor.
 * The basic control methods are:
 * <code>forward, backward, reverseDirection, stop</code>
 * and <code>flt</code>. To set each motor's velocity, use {@link #setSpeed(int)
 * <code>setSpeed  </code> }.
 * The maximum velocity of the motor is limited by the battery voltage and load.
 * With no load, the maximum degrees per second is about 100 times the voltage
 * (for the large EV3 motor).  <br>
 * The velocity is regulated by comparing the tacho count with velocity times elapsed
 * time, and adjusting motor power to keep these closely matched. Changes in velocity
 * will be made at the rate specified via the
 * <code> setAcceleration(int acceleration)</code> method.
 * The methods <code>rotate(int angle) </code> and <code>rotateTo(int ange)</code>
 * use the tachometer to control the position at which the motor stops, usually within 1 degree
 * or 2.<br>
 * <br> <b> Listeners.</b>  An object implementing the {@link lejos.robotics.RegulatedMotorListener
 * <code> RegulatedMotorListener </code> } interface  may register with this class.
 * It will be informed each time the motor starts or stops.
 * <br> <b>Stall detection</b> If a stall is detected or if for some other reason
 * the speed regulation fails, the motor will stop, and
 * <code>isStalled()</code >  returns <b>true</b>.
 * <br>Motors will hold their position when stopped. If this is not what you require use
 * the flt() method instead of stop().
 * <p>
 * TODO: Fix the name
 *
 * @author Roger Glassey
 * @author Andy Shaw
 * @author Juan Antonio Breña Moral
 */
public abstract class BaseRegulatedMotor
    extends GenericMotor { // implements RegulatedMotor

  private final AtomicInteger countPerRotation = new AtomicInteger(-1);
  private final AtomicInteger maxSpeed         = new AtomicInteger(-1);
  private final AtomicBoolean fixedSpeed       = new AtomicBoolean(true);

  private int speed = 0;

  protected BaseRegulatedMotor(final Class<?> deviceClass, final MotorPort portName) {
    // PRA super(LEGO_PORT, portName);
    super(deviceClass, TACHO_MOTOR, portName);

    // PRA this.setAttribute(MODE, CommandValue.TACHO_MOTOR);
    // PRA this.detectWithException(TACHO_MOTOR, portName);
    // PRA this.setAttribute(SPEED_REGULATION, SPEED_REGULATION_ON);
  }

  private AttributeName getSpeedAttrib() { return this.fixedSpeed.get() ? FIXED_SPEED_VAL : VARIABLE_SPEED_VAL; }

  /**
   * @return the current tachometer count.
   * @see lejos.robotics.RegulatedMotor#getTachoCount()
   */
  public int getTachoCount() { return this.getIntegerAttribute(POSITION_VAL); }

  /**
   * Returns the current position that the motor regulator is trying to
   * maintain. Normally this will be the actual position of the motor and will
   * be the same as the value returned by getTachoCount(). However in some
   * circumstances (activeMotors that are in the process of stalling, or activeMotors
   * that have been forced out of position), the two values may differ. Note that
   * if regulation has been suspended calling this method will restart it.
   *
   * @return the current position calculated by the regulator.
   */
  public float getPosition() { return this.getFloatAttribute(CURRENT_POSITION); }

  public void runVariable() { this.command(RUN_DIRECT); }

  public void runForever() { this.command(RUN_FOREVER); }

  public void advanceBy(final int position) {
    this.setAttribute(POSITION_VAL, position);
    this.command(RUN_TO_REL_POS);
  }

  public void advanceTo(final int position) {
    this.setAttribute(POSITION_VAL, position);
    this.command(RUN_TO_ABS_POS);
  }

  public void runForSecs(final int secs) {
    this.setAttribute(TIME_TO_RUN_VAL, secs * 1000);
    this.command(RUN_TIMED);
  }

  public void runForMillis(final int millis) {
    this.setAttribute(TIME_TO_RUN_VAL, millis);
    this.command(RUN_TIMED);
  }

  public int getCountPerRotation() {
    if (this.countPerRotation.get() == -1)
      this.countPerRotation.compareAndSet(-1, this.getIntegerAttribute(COUNT_PER_ROT));
    return this.countPerRotation.get();
  }

  public int getMaxSpeed() {
    if (this.maxSpeed.get() == -1)
      this.maxSpeed.compareAndSet(-1, this.getIntegerAttribute(MAX_SPEED));
    return this.maxSpeed.get();
  }

  // This sets the mode but does not actually stop the motor

  /**
   * Set the motor into float mode. This will stop the motor without braking
   * and the position of the motor will not be maintained.
   */
  public void flt() { this.command(COAST); }

  /**
   * Causes motor to stop, pretty much
   * instantaneously. In other words, the
   * motor doesn't just stop; it will resist
   * any further motion.
   * Cancels any rotate() orders in progress
   */
  public void stop() { this.command(STOP); }

  /**
   * Sets desired motor speed , in degrees per second;
   * The maximum reliably sustainable velocity is  100 x battery voltage under
   * moderate load, such as a direct drive robot on the level.
   *
   * @param speed value in degrees/sec
   */
  public void setSpeed(int speed) {
    this.speed = speed;
    this.fixedSpeed.set(true);
    this.setAttribute(POLARITY, this.speed >= 0 ? NORMAL_POLARITY : INVERSED_POLARITY);
    this.setAttribute(this.getSpeedAttrib(), Math.abs(this.speed));
  }

  public boolean isForward() {
    final String polarity = this.getStringAttribute(POLARITY);
    return NORMAL_POLARITY.attribValue().equals(polarity);
  }

  public void setVariableSpeed(int speed) {
    this.speed = speed;
    this.fixedSpeed.set(false);
    this.setAttribute(this.getSpeedAttrib(), this.speed);
  }

  public void reset() { this.command(RESET); }

  /**
   * Rotate by the request number of degrees.
   *
   * @param angle           number of degrees to rotate relative to the current position
   * @param immediateReturn if true do not wait for the move to complete
   *                        Rotate by the requested number of degrees. Wait for the move to complete.
   * @param angle
   */
  public void rotate(int angle, boolean immediateReturn) {
    this.setAttribute(POSITION_VAL, angle);
    this.command(RUN_TO_REL_POS);

    if (!immediateReturn) {
      while (this.isMoving()) {
        // do stuff or do nothing
        // possibly sleep for some short interval to not block
      }
    }
  }

  /**
   * Rotate by the requested number of degrees. Wait for the move to complete.
   *
   * @param angle
   */
  public void rotate(int angle) {
    rotate(angle, false);
  }

  public void rotateTo(int limitAngle, boolean immediateReturn) {
    this.setAttribute(POSITION_VAL, limitAngle);
    this.command(RUN_TO_ABS_POS);

    if (!immediateReturn) {
      while (this.isMoving()) {
        // do stuff or do nothing
        // possibly sleep for some short interval to not block
      }
    }
  }

  /**
   * Rotate to the target angle. Do not return until the move is complete.
   *
   * @param limitAngle Angle to rotate to.
   */
  public void rotateTo(int limitAngle) { rotateTo(limitAngle, false); }

  /**
   * Return the current target speed.
   *
   * @return the current target speed.
   */
  public int getSpeed() { return this.getIntegerAttribute(this.getSpeedAttrib()); }

  /**
   * Return the current velocity.
   *
   * @return current velocity in degrees/s
   */
  public int getRotationSpeed() { return 0;/*Math.round(reg.getCurrentVelocity());*/ }

  public void setCoastStop() { this.setStop(COAST); }

  public boolean isCoastStop() { return this.getStop() == COAST; }

  public void setBrakeStop() { this.setStop(BRAKE); }

  public boolean isBrakeStop() { return this.getStop() == BRAKE; }

  public void setHoldStop() { this.setStop(HOLD); }

  public boolean isHoldStop() { return this.getStop() == HOLD; }

  private void setStop(final AttributeValue value) { this.setAttribute(STOP_ACTION, value); }

  private AttributeValue getStop() {
    final String currVal = this.getStringAttribute(STOP_ACTION);
    for (final AttributeValue attribVal : AttributeValue.values()) {
      if (attribVal.attribValue().equals(currVal))
        return attribVal;
    }
    throw new IllegalArgumentException(format("Invalid stop value: %s", currVal));
  }

  /**
   * This method returns <b>true </b> if the motor is attempting to rotate.
   * The return value may not correspond to the actual motor movement.<br>
   * For example,  If the motor is stalled, isMoving()  will return <b> true. </b><br>
   * After flt() is called, this method will return  <b>false</b> even though the motor
   * axle may continue to rotate by inertia.
   * If the motor is stalled, isMoving()  will return <b> true. </b> . A stall can
   * be detected  by calling {@link #isStalled()};
   *
   * @return true iff the motor is attempting to rotate.<br>
   */
  public boolean isMoving() { return (this.getStringAttribute(STATE).contains(RUNNING_STATE.attribValue())); }

  /**
   * Return true if the motor is currently stalled.
   *
   * @return true if the motor is stalled, else false
   */
  public boolean isStalled() { return this.getStringAttribute(STATE).contains(STALLED_STATE.attribValue()); }

  /**
   * @see lejos.hardware.motor.BasicMotor#forward()
   */
  public void forward() {
    if (this.speed != 0)
      this.setAttribute(this.getSpeedAttrib(), Math.abs(this.speed));
    this.command(RUN_FOREVER);
  }

  /**
   * @see lejos.hardware.motor.BasicMotor#backward()
   */
  public void backward() {
    if (this.speed != 0)
      this.setAttribute(this.getSpeedAttrib(), Math.abs(this.speed) * -1);
    this.command(RUN_FOREVER);
  }

  /**
   * Reset the tachometer associated with this motor. Note calling this method
   * will cause any current move operation to be halted.
   */
  public void resetTachoCount() {
    this.command(RESET);
    this.fixedSpeed.set(true);
  }
}
