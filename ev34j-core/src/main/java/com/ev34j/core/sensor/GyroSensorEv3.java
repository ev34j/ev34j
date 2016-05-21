package com.ev34j.core.sensor;

import com.ev34j.core.utils.Ev3DevFs;

import java.io.File;

import static com.ev34j.core.sensor.ModeType.ANGLE_DEGREES;
import static com.ev34j.core.sensor.ModeType.ROTATIONAL_SPEED;
import static com.ev34j.core.sensor.SensorValue.VALUE0;

/**
 * <b>EV3 Gyro sensor</b><br>
 * The digital EV3 Gyro Sensor measures the sensors rotational motion and changes in its orientation.
 * <p>
 * <p>
 * <p>
 * <table border=1>
 * <tr>
 * <th colspan=4>Supported modes</th>
 * </tr>
 * <tr>
 * <th>Mode name</th>
 * <th>Description</th>
 * <th>unit(s)</th>
 * <th>Getter</th>
 * </tr>
 * <tr>
 * <td>Angle</td>
 * <td>Measures the orientation of the sensor</td>
 * <td>Degrees</td>
 * <td> {@link #getAngleDegreesMode() }</td>
 * </tr>
 * <tr>
 * <td>Rate</td>
 * <td>Measures the angular velocity of the sensor</td>
 * <td>Degrees / second</td>
 * <td> {@link #getRateMode() }</td>
 * </tr>
 * <tr>
 * <td>Rate and Angle</td>
 * <td>Measures both angle and angular velocity</td>
 * <td>Degrees, Degrees / second</td>
 * <td> {@link #getAngleAndRateMode() }</td>
 * </tr>
 * </table>
 * <p>
 * <p>
 * <p>
 * <b>Sensor configuration</b><br>
 * Use {@link #reset()} to recalibrate the sensor and to reset accumulated angle to zero. Keep the sensor motionless during a reset.
 * The sensor shuld also be motionless during initialization.
 * <p>
 * <p>
 * <p>
 * See <a href="http://www.ev-3.net/en/archives/849"> Sensor Product page </a>
 * See <a href="http://sourceforge.net/p/lejos/wiki/Sensor%20Framework/"> The
 * leJOS sensor framework</a>
 * See {@link lejos.robotics.SampleProvider leJOS conventions for
 * SampleProviders}
 * <p>
 * <p>
 *
 * @author Andy Shaw
 * @author Aswin Bouwmeester
 * @author Juan Antonio Breña Moral
 */
public class GyroSensorEv3
    extends GenericSensor {

  public GyroSensorEv3(final Class<?> deviceClass, final SensorPort sensorPort, final SensorSetting sensorSetting) {
    super(deviceClass, sensorPort, sensorSetting.getDriverType(), sensorSetting.getModuleType(), true);
    assignModes(new AngleDegreesMode(this.getDevicePath()),
                new RotationalSpeedMode(this.getDevicePath()));
  }

  /**
   * Hardware calibration of the Gyro sensor and reset off accumulated angle to zero. <br>
   * The sensor should be motionless during calibration.
   */
  public void reset() {
    if (this.getModeType() == ANGLE_DEGREES) {
      this.switchMode(ROTATIONAL_SPEED);
      this.switchMode(ANGLE_DEGREES);
    }
    else {
      this.switchMode(ANGLE_DEGREES);
      this.switchMode(ROTATIONAL_SPEED);
    }
  }

  /**
   * <b>EV3 Gyro sensor, Angle mode</b><br>
   * Measures the orientation of the sensor in respect to its start orientation.
   * <p>
   * <p>
   * <b>Size and content of the sample</b><br>
   * The sample contains one elements representing the orientation (in Degrees) of the sensor in respect to its start position.
   * <p>
   * <p>
   * <b>Configuration</b><br>
   * The start position can be set to the current position using the reset method of the sensor.
   *
   * @return A sampleProvider
   * See {@link lejos.robotics.SampleProvider leJOS conventions for
   * SampleProviders}
   */
  public SensorMode getAngleDegreesMode() { return this.getSensorMode(ANGLE_DEGREES); }

  private class AngleDegreesMode
      extends SensorMode {

    private AngleDegreesMode(File devicePath) {
      super(ANGLE_DEGREES, devicePath, VALUE0);
    }

    @Override
    public void fetchSample(final float[] sample, final int offset) {
      switchMode(this.getModeType());
      sample[offset] = Ev3DevFs.readFloat(this.getSensorPath(offset));
    }
  }

  public SensorMode getRotationalSpeedMode() { return this.getSensorMode(ROTATIONAL_SPEED); }

  private class RotationalSpeedMode
      extends SensorMode {

    private RotationalSpeedMode(File devicePath) {
      super(ROTATIONAL_SPEED, devicePath, VALUE0);
    }

    @Override
    public void fetchSample(final float[] sample, final int offset) {
      switchMode(this.getModeType());
      sample[offset] = Ev3DevFs.readFloat(this.getSensorPath(offset));
    }
  }
}
