package com.ev34j.core.sensor;

import com.ev34j.core.utils.Ev3DevFs;

import java.io.File;

import static com.ev34j.core.sensor.ModeType.TOUCH;
import static com.ev34j.core.sensor.SensorValue.VALUE0;

/**
 * <b>Lego EV3 Touch sensor</b><br>
 * The analog EV3 Touch Sensor is a simple but exceptionally precise tool that detects when its front button is pressed or released.
 * <p>
 * *
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
 * <td>Touch</td>
 * <td>Detects when its front button is pressed</td>
 * <td>Binary</td>
 * <td> {@link #getTouchMode() }</td>
 * </tr>
 * </table>
 * <p>
 * <p>
 * <p>
 * <p>
 * See <a href="http://www.ev-3.net/en/archives/846"> Sensor Product page </a>
 * See <a href="http://sourceforge.net/p/lejos/wiki/Sensor%20Framework/"> The
 * leJOS sensor framework</a>
 * See <a href="http://www.ev3dev.org/docs/sensors/#uart-sensors"> The UART Sensors</a>
 * See {@link lejos.robotics.SampleProvider leJOS conventions for
 * SampleProviders}
 * <p>
 * <p>
 */
public class TouchSensor
    extends GenericSensor {

  public TouchSensor(final Class<?> deviceClass,
                     final SensorPort sensorPort,
                     final ConnnectionType connnectionType,
                     final SensorType sensorType) {
    super(deviceClass, sensorPort, connnectionType, sensorType, false);
    this.assignModes(new TouchMode(this.getDevicePath()));
  }

  public boolean isPressed() {
    final SensorMode mode = this.getTouchMode();
    float[] vals = new float[1];
    mode.fetchSample(vals, 0);
    return vals[0] > 0;
  }

  /**
   * <b>Lego EV3 Touch sensor, Touch mode</b><br>
   * Detects when its front button is pressed
   * <p>
   * <p>
   * <b>Size and content of the sample</b><br>
   * The sample contains one element, a value of 0 indicates that the button is not presse, a value of 1 indicates the button is pressed.
   * <p>
   * <p>
   *
   * @return A sampleProvider
   * See {@link lejos.robotics.SampleProvider leJOS conventions for
   * SampleProviders}
   */
  public SensorMode getTouchMode() { return this.getSensorMode(TOUCH); }

  private class TouchMode
      extends SensorMode {

    private TouchMode(final File devicePath) {
      super(TOUCH, devicePath, VALUE0);
    }

    @Override
    public void fetchSample(final float[] sample, final int offset) {
      switchMode(this.getModeType());
      sample[offset] = Ev3DevFs.readFloat(this.getSensorPath(0));
    }
  }
}
