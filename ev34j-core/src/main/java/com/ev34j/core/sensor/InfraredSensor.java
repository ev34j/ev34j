package com.ev34j.core.sensor;

import com.ev34j.core.utils.Ev3DevFs;

import java.io.File;

import static com.ev34j.core.sensor.ModeType.IR_DISTANCE;
import static com.ev34j.core.sensor.SensorValue.VALUE0;

/**
 * <b>EV3 Infra Red sensor</b><br>
 * The digital EV3 Infrared Seeking Sensor detects proximity to the robot and reads signals emitted by the EV3 Infrared Beacon. The sensor can alse be used as a receiver for a Lego Ev3 IR remote control.
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
 * <td>Measures the distance to an object in front of the sensor</td>
 * <td>Undefined</td>
 * <td> {@link #getDistanceMode() }</td>
 * </tr>
 * <tr>
 * <td>Seek</td>
 * <td>Locates up to four beacons</td>
 * <td>Undefined, undefined</td>
 * <td> {@link #getSeekMode() }</td>
 * </tr>
 * </table><p>
 * <p>
 * <b>EV3 Infra Red sensor</b><br>
 * <p>
 * The sensor can be used as a receiver for up to four Lego Ev3 IR remote controls using the {@link #getRemoteCommand} and {@link #getRemoteCommands} methods.
 * <p>
 * <p>
 * See <a href="http://www.ev-3.net/en/archives/848"> Sensor Product page </a>
 * See <a href="http://sourceforge.net/p/lejos/wiki/Sensor%20Framework/"> The
 * leJOS sensor framework</a>
 * See {@link lejos.robotics.SampleProvider leJOS conventions for
 * SampleProviders}
 * <p>
 * <p>
 *
 * @author Andy Shaw
 * @author Juan Antonio Breña Moral
 */
public class InfraredSensor
    extends GenericSensor {

  public InfraredSensor(final Class<?> deviceClass, final SensorPort sensorPort) {
    super(deviceClass, sensorPort, DriverType.EV3_UART, ModuleType.EV3_IR, true);
    this.assignModes(new DistanceMode(this.getDevicePath()));
  }

  /**
   * <b>EV3 Infra Red sensor, Distance mode</b><br>
   * Measures the distance to an object in front of the sensor.
   * <p>
   * <p>
   * <b>Size and content of the sample</b><br>
   * The sample contains one element giving the distance to an object in front of the sensor. The distance provided is very roughly equivalent to meters
   * but needs conversion to give better distance. See product page for details. <br>
   * The effective range of the sensor in Distance mode  is about 5 to 50 centimeters. Outside this range a zero is returned
   * for low values and positive infinity for high values.
   *
   * @return A sampleProvider
   * See {@link lejos.robotics.SampleProvider leJOS conventions for
   * SampleProviders}
   * See <a href="http://www.ev-3.net/en/archives/848"> Sensor Product page </a>
   */
  public SensorMode getDistanceMode() { return this.getSensorMode(IR_DISTANCE); }

  private class DistanceMode
      extends SensorMode {

    private DistanceMode(File devicePath) {
      super(IR_DISTANCE, devicePath, VALUE0);
    }

    @Override
    public void fetchSample(final float[] sample, final int offset) {
      switchMode(this.getModeType());
      final float raw = Ev3DevFs.readFloat(this.getSensorPath(offset));
      sample[offset] = raw < 0 ? 0 : raw;

      /*
      if (raw < 5)
        sample[offset] = 0;
      else if (raw > 55)
        sample[offset] = Float.POSITIVE_INFINITY;
      else
        sample[offset] = raw * toSI;
      */
    }
  }
}
