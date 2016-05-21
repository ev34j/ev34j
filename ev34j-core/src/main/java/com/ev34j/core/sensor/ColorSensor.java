package com.ev34j.core.sensor;

import com.ev34j.core.utils.Ev3DevFs;

import java.io.File;

import static com.ev34j.core.sensor.ModeType.AMBIENT;
import static com.ev34j.core.sensor.ModeType.COLOR_ID;
import static com.ev34j.core.sensor.ModeType.REFLECTED;
import static com.ev34j.core.sensor.ModeType.RGB;
import static com.ev34j.core.sensor.SensorColor.BLACK;
import static com.ev34j.core.sensor.SensorColor.BLUE;
import static com.ev34j.core.sensor.SensorColor.BROWN;
import static com.ev34j.core.sensor.SensorColor.GREEN;
import static com.ev34j.core.sensor.SensorColor.NONE;
import static com.ev34j.core.sensor.SensorColor.RED;
import static com.ev34j.core.sensor.SensorColor.WHITE;
import static com.ev34j.core.sensor.SensorColor.YELLOW;
import static com.ev34j.core.sensor.SensorValue.VALUE0;
import static com.ev34j.core.sensor.SensorValue.VALUE1;
import static com.ev34j.core.sensor.SensorValue.VALUE2;


/**
 * <b>EV3 color sensor</b><br>
 * The digital EV3 Color Sensor distinguishes between eight different colors. It also serves as a light sensor by detecting light intensities.
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
 * <td>Color ID</td>
 * <td>Measures the color ID of a surface</td>
 * <td>Color ID</td>
 * <td> {@link #getColorIdMode() }</td>
 * </tr>
 * <tr>
 * <td>Red</td>
 * <td>Measures the intensity of a reflected red light </td>
 * <td>N/A, Normalized to (0-1) </td>
 * <td> {@link #getReflectedMode() }</td>
 * </tr>
 * <tr>
 * <td>RGB</td>
 * <td>Measures the RGB color of a surface</td>
 * <td>N/A, Normalized to (0-1)</td>
 * <td> {@link #getRGBMode() }</td>
 * </tr>
 * <tr>
 * <td>Ambient</td>
 * <td>Measures the ambient light level</td>
 * <td>N/A, Normalized to (0-1)</td>
 * <td> {@link #getAmbientMode() }</td>
 * </tr>
 * </table>
 * <p>
 * <p>
 * <p>
 * <b>Sensor configuration</b><br>
 * The flood light of the sensor can be put on or off using the setFloodlight methods.
 * <p>
 * <p>
 * <p>
 * See <a href="http://www.ev-3.net/en/archives/847"> Sensor Product page </a>
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
public class ColorSensor
    extends GenericSensor {
  // implements LampController, ColorIdentifier {

  // TODO: decide what to do to the LampController and ColorIdentifier interfaces
  private static SensorColor[] colorMap = {
      NONE, BLACK, BLUE, GREEN, YELLOW, RED, WHITE, BROWN
  };

  // following maps operating mode to lamp color
  // NONE, BLACK, BLUE, GREEN, YELLOW, RED, WHITE, BROWN
  private static final SensorColor[] lightColor = {NONE, RED, BLUE, WHITE, WHITE, WHITE, WHITE};

  private short[] raw = new short[3];

  public ColorSensor(final Class<?> deviceClass, final SensorPort sensorPort, final SensorSetting sensorSetting) {
    super(deviceClass, sensorPort, sensorSetting.getDriverType(), sensorSetting.getModuleType(), true);
    this.assignModes(new ColorIdMode(this.getDevicePath()),
                     new ReflectedMode(this.getDevicePath()),
                     new RGBMode(this.getDevicePath()),
                     new AmbientMode(this.getDevicePath()));
  }

  /*
  public int getColorID() {
    setFloodlight(WHITE);
    return 0; //colorMap[port.getByte()];
  }

  public void setFloodlight(final boolean floodlight) { this.setFloodlight(floodlight ? RED : NONE); }

  public boolean isFloodlightOn() { return lightColor[this.getCurrentModeName() + 1] != NONE; }

  public SensorColor getFloodlight() { return lightColor[this.getCurrentModeName() + 1]; }

  public void setFloodlight(final SensorColor color) {
    final ModeName modeName;
    switch (color) {
      case BLUE:
        modeName = AMBIENT;
        break;
      case WHITE:
        modeName = COLOR_ID;
        break;
      case RED:
        modeName = REFLECTED;
        break;
      case NONE:
        modeName = COLOR_RESET;
        break;
      default:
        throw new IllegalArgumentException(format("Invalid color specified: %s", color));
    }
    this.switchMode(modeName);
  }
  */


  /**
   * <b>EV3 color sensor, Color ID mode</b><br>
   * Measures the color ID of a surface. The sensor can identify 8 unique colors (NONE, BLACK, BLUE, GREEN, YELLOW, RED, WHITE, BROWN).
   * <p>
   * <p>
   * <b>Size and content of the sample</b><br>
   * The sample contains one element containing the ID (0-7) of the detected color.
   * <p>
   * <p>
   *
   * @return A sampleProvider
   * See {@link lejos.robotics.SampleProvider leJOS conventions for
   * SampleProviders}
   */
  public SensorMode getColorIdMode() { return this.getSensorMode(COLOR_ID); }

  private class ColorIdMode
      extends SensorMode {

    private ColorIdMode(final File devicePath) {
      super(COLOR_ID, devicePath, VALUE0);
    }

    @Override
    public void fetchSample(final float[] sample, final int offset) {
      switchMode(this.getModeType());
      sample[offset] = Ev3DevFs.readFloat(this.getSensorPath(offset));
    }
  }

  /**
   * <b>EV3 color sensor, Red mode</b><br>
   * Measures the level of reflected light from the sensors RED LED.
   * <p>
   * <p>
   * <b>Size and content of the sample</b><br>
   * The sample contains one element containing the intensity level (Normalized between 0 and 1) of reflected light.
   * <p>
   * <p>
   *
   * @return A sampleProvider
   * See {@link lejos.robotics.SampleProvider leJOS conventions for
   * SampleProviders}
   */
  public SensorMode getReflectedMode() { return this.getSensorMode(REFLECTED); }

  private class ReflectedMode
      extends SensorMode {

    private ReflectedMode(File devicePath) {
      super(REFLECTED, devicePath, VALUE0);
    }

    @Override
    public void fetchSample(final float[] sample, final int offset) {
      switchMode(this.getModeType());
      sample[offset] = Ev3DevFs.readFloat(this.getSensorPath(offset));
    }
  }

  /**
   * get a sample provider that returns the light values (RGB) when illuminated by a
   * white light source.
   * @return the sample provider
   */
  /**
   * <b>EV3 color sensor, RGB mode</b><br>
   * Measures the level of red, green and blue light when illuminated by a white light source..
   * <p>
   * <p>
   * <b>Size and content of the sample</b><br>
   * The sample contains 3 elements containing the intensity level (Normalized between 0 and 1) of red, green and blue light respectivily.
   * <p>
   * <p>
   *
   * @return A sampleProvider
   * See {@link lejos.robotics.SampleProvider leJOS conventions for
   * SampleProviders}
   */
  public SensorMode getRGBMode() {
    //TODO: Should this return 3 or 4 values, 4 values would require an extra mode switch to get ambient value.
    return this.getSensorMode(RGB);
  }

  private class RGBMode
      extends SensorMode {

    private RGBMode(File devicePath) {
      super(RGB, devicePath, VALUE0, VALUE1, VALUE2);
    }

    @Override
    public int sampleSize() { return 3; }

    @Override
    public void fetchSample(final float[] sample, final int offset) {
      switchMode(this.getModeType());
      sample[offset] = Ev3DevFs.readFloat(this.getSensorPath(0));
      sample[offset + 1] = Ev3DevFs.readFloat(this.getSensorPath(1));
      sample[offset + 2] = Ev3DevFs.readFloat(this.getSensorPath(2));
    }
  }

  /**
   * <b>EV3 color sensor, Ambient mode</b><br>
   * Measures the level of ambient light while the sensors lights are off.
   * <p>
   * <p>
   * <b>Size and content of the sample</b><br>
   * The sample contains one element containing the intensity level (Normalized between 0 and 1) of ambient light.
   * <p>
   * <p>
   *
   * @return A sampleProvider
   * See {@link lejos.robotics.SampleProvider leJOS conventions for
   * SampleProviders}
   */
  public SensorMode getAmbientMode() { return this.getSensorMode(AMBIENT); }

  private class AmbientMode
      extends SensorMode {

    private AmbientMode(File devicePath) {
      super(AMBIENT, devicePath, VALUE0);
    }

    @Override
    public void fetchSample(final float[] sample, final int offset) {
      switchMode(this.getModeType());
      sample[offset] = Ev3DevFs.readFloat(this.getSensorPath(offset));
    }
  }
}
