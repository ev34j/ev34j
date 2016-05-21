package com.ev34j.core.sensor;


import com.ev34j.core.common.DeviceNotSupportedException;
import com.ev34j.core.common.Platform;
import com.ev34j.core.utils.Ev3DevFs;

import java.io.File;

import static com.ev34j.core.sensor.ModeType.CONT_DISTANCE_CMS;
import static com.ev34j.core.sensor.ModeType.CONT_DISTANCE_INCHES;
import static com.ev34j.core.sensor.ModeType.SINGLE_DISTANCE_CMS;
import static com.ev34j.core.sensor.ModeType.SINGLE_DISTANCE_INCHES;
import static com.ev34j.core.sensor.ModeType.US_LISTEN;
import static com.ev34j.core.sensor.SensorValue.VALUE0;

/**
 * <b>Lego EV3 Ultrasonic sensor</b><br>
 * The EV3 Ultrasonic sensor measures distance to an object in front of the
 * sensor. It can also be used to detect other (active) Ultrasonic sensors in
 * the vicinity.
 * <p>
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
 * <td>Distance</td>
 * <td>Measures distance to an object in front of the sensor</td>
 * <td>Meter</td>
 * <td> {@link #getDistanceCmMode() }</td>
 * </tr>
 * <tr>
 * <td>Listen</td>
 * <td>Listens for other ultrasonic sensors</td>
 * <td>Boolean</td>
 * <td> {@link #getListenMode() }</td>
 * </tr>
 * </table>
 * <p>
 * <p>
 * <p>
 * <b>Sensor configuration</b><br>
 * The sensor can be switched off and on using the {@link #enable} and
 * {@link #disable} methods. Disabling the sensor also shuts down the lights.
 * <p>
 * <p>
 * <p>
 * See <a href="http://www.ev-3.net/en/archives/844"> Sensor Product page </a>
 * See <a href="http://sourceforge.net/p/lejos/wiki/Sensor%20Framework/"> The
 * leJOS sensor framework</a>
 * See {@link lejos.robotics.SampleProvider leJOS conventions for
 * SampleProviders}
 * <p>
 * <p>
 *
 * @author Aswin Bouwmeester
 * @author Juan Antonio Breña Moral
 */
public class UltrasonicSensor
    extends GenericSensor {

  public UltrasonicSensor(final Class<?> deviceClass, final SensorPort sensorPort, final SensorSetting sensorSetting) {
    super(deviceClass, sensorPort, sensorSetting.getDriverType(), sensorSetting.getModuleType(), true);

    // See: http://www.mindsensors.com/forum/topic/151_nxt-ultrasonic-sensor-in-pistorms
    if (Platform.isPiStorm())
      throw new DeviceNotSupportedException(this.getClass());

    this.assignSensorModes(new ContinuousDistanceCmsMode(this.getDevicePath()),
                           new ContinuousDistanceInchesMode(this.getDevicePath()),
                           new SingleDistanceCmsMode(this.getDevicePath()),
                           new SingleDistanceInchesMode(this.getDevicePath()),
                           new ListenMode(this.getDevicePath()));
  }

  /**
   * <b>Lego EV3 Ultrasonic sensor, Listen mode</b><br>
   * Listens for the presence of other ultrasonic sensors.
   * <p>
   * <p>
   * <b>Size and content of the sample</b><br>
   * The sample contains one elements indicating the presence of another ultrasonic sensor.
   * A value of 1 indicates that the sensor detects another ultrasonic sensor.
   *
   * @return A sampleProvider
   */
  public SensorMode getListenMode() { return this.getSensorMode(US_LISTEN); }

  /**
   * <b>Lego EV3 Ultrasonic sensor, Distance mode</b><br>
   * Measures distance to an object in front of the sensor
   * <p>
   * <p>
   * <b>Size and content of the sample</b><br>
   * The sample contains one elements representing the distance (in metres) to an object in front of the sensor.
   * unit).
   *
   * @return A sampleProvider
   */
  public SensorMode getDistanceCmMode(final boolean continuous) {
    return this.getSensorMode(continuous ? CONT_DISTANCE_CMS : SINGLE_DISTANCE_CMS);
  }

  public SensorMode getDistanceInchesMode(final boolean continuous) {
    return this.getSensorMode(continuous ? CONT_DISTANCE_INCHES : SINGLE_DISTANCE_INCHES);
  }

  private abstract class AbstractDistanceMode
      extends SensorMode {
    public AbstractDistanceMode(final File devicePath, final ModeType modeType, final SensorValue... sensorValues) {
      super(modeType, devicePath, sensorValues);
    }

    @Override
    public void fetchSample(final float[] sample, final int offset) {
      switchMode(this.getModeType());
      sample[0] = Math.max(0F, Ev3DevFs.readFloat(this.getSensorPath(offset)));
    }
  }

  public SensorMode getContinuousDistanceCmsMode() { return this.getSensorMode(CONT_DISTANCE_CMS); }

  private class ContinuousDistanceCmsMode
      extends AbstractDistanceMode {
    private ContinuousDistanceCmsMode(File devicePath) {
      super(devicePath, CONT_DISTANCE_CMS, VALUE0);
    }
  }

  public SensorMode getContinuousDistanceInchesMode() { return this.getSensorMode(CONT_DISTANCE_INCHES); }

  private class ContinuousDistanceInchesMode
      extends AbstractDistanceMode {

    private ContinuousDistanceInchesMode(File devicePath) {
      super(devicePath, CONT_DISTANCE_INCHES, VALUE0);
    }
  }

  public SensorMode getSingleDistanceCmsMode() { return this.getSensorMode(SINGLE_DISTANCE_CMS); }

  private class SingleDistanceCmsMode
      extends AbstractDistanceMode {

    private SingleDistanceCmsMode(File devicePath) {
      super(devicePath, SINGLE_DISTANCE_CMS, VALUE0);
    }
  }

  public SensorMode getSingleDistanceInchesMode() { return this.getSensorMode(SINGLE_DISTANCE_INCHES); }

  private class SingleDistanceInchesMode
      extends AbstractDistanceMode {

    private SingleDistanceInchesMode(File devicePath) {
      super(devicePath, SINGLE_DISTANCE_INCHES, VALUE0);
    }
  }

  public SensorMode getListenerMode() { return this.getSensorMode(US_LISTEN); }

  /**
   * Represents a Ultrasonic sensor in listen mode
   */
  private class ListenMode
      extends SensorMode {

    private ListenMode(File devicePath) {
      super(US_LISTEN, devicePath, VALUE0);
    }

    @Override
    public void fetchSample(final float[] sample, final int offset) {
      switchMode(this.getModeType());
      sample[0] = Ev3DevFs.readFloat(this.getSensorPath(offset));
    }
  }
}
