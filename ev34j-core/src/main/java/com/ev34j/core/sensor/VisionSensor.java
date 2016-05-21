package com.ev34j.core.sensor;

import com.ev34j.core.utils.Ev3DevFs;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.ev34j.core.sensor.ModeType.SIG1;
import static com.ev34j.core.sensor.ModeType.SIG2;
import static com.ev34j.core.sensor.ModeType.SIG3;
import static com.ev34j.core.sensor.ModeType.SIG4;
import static com.ev34j.core.sensor.ModeType.SIG5;
import static com.ev34j.core.sensor.ModeType.SIG6;
import static com.ev34j.core.sensor.ModeType.SIG7;
import static com.ev34j.core.sensor.SensorValue.VALUE0;
import static com.ev34j.core.sensor.SensorValue.VALUE1;
import static com.ev34j.core.sensor.SensorValue.VALUE2;
import static com.ev34j.core.sensor.SensorValue.VALUE3;
import static com.ev34j.core.sensor.SensorValue.VALUE4;
import static com.ev34j.core.sensor.SensorValue.VALUE5;
import static com.ev34j.core.sensor.SensorValue.VALUE6;

public class VisionSensor
    extends GenericSensor {

  private final ModeType[] sigModeTypes = {SIG1, SIG2, SIG3, SIG4, SIG5, SIG6, SIG7};

  public VisionSensor(final Class<?> deviceClass,
                      final SensorPort sensorPort,
                      final DriverType driverType,
                      final ModuleType moduleType) {
    super(deviceClass, sensorPort, driverType, moduleType, true);

    final List<SensorMode> sensorModes = new ArrayList<>();
    sensorModes.add(new AllSignaturesMode(this.getDevicePath()));
    sensorModes.add(new ColorCodeMode(this.getDevicePath()));
    sensorModes.add(new AngleMode(this.getDevicePath()));
    for (ModeType modeType : this.sigModeTypes)
      sensorModes.add(new SingleSignatureMode(modeType, this.getDevicePath()));

    this.assignSensorModes(sensorModes.toArray(new SensorMode[sensorModes.size()]));
  }

  public SensorMode getAllSignaturesMode() { return this.getSensorMode(ModeType.ALL_SIGS); }

  public SensorMode getSingleSignatureMode(final int pos) { return this.getSensorMode(this.sigModeTypes[pos]); }

  public SensorMode getColorCodeMode() { return this.getSensorMode(ModeType.COLOR_CODE); }

  public SensorMode getAngleMode() { return this.getSensorMode(ModeType.ANGLE); }

  private class AllSignaturesMode
      extends SensorMode {

    private AllSignaturesMode(final File devicePath) {
      super(ModeType.ALL_SIGS, devicePath, VALUE0, VALUE1, VALUE2, VALUE3, VALUE4, VALUE5, VALUE6);
    }

    @Override
    public int sampleSize() { return 7; }

    @Override
    public void fetchSample(final float[] sample, final int offset) {
      switchMode(this.getModeType());
      sample[0] = Ev3DevFs.readFloat(this.getSensorPath(0));
      sample[1] = Ev3DevFs.readFloat(this.getSensorPath(1));
      sample[2] = Ev3DevFs.readFloat(this.getSensorPath(2));
      sample[3] = Ev3DevFs.readFloat(this.getSensorPath(3));
      sample[4] = Ev3DevFs.readFloat(this.getSensorPath(4));
      sample[5] = Ev3DevFs.readFloat(this.getSensorPath(5));
      sample[6] = Ev3DevFs.readFloat(this.getSensorPath(6));
    }
  }

  private class SingleSignatureMode
      extends SensorMode {

    private SingleSignatureMode(final ModeType modeType, final File devicePath) {
      super(modeType, devicePath, VALUE0, VALUE1, VALUE2, VALUE3, VALUE4);
    }

    @Override
    public int sampleSize() { return 5; }

    @Override
    public void fetchSample(final float[] sample, final int offset) {
      switchMode(this.getModeType());
      sample[0] = Ev3DevFs.readFloat(this.getSensorPath(0));
      sample[1] = Ev3DevFs.readFloat(this.getSensorPath(1));
      sample[2] = Ev3DevFs.readFloat(this.getSensorPath(2));
      sample[3] = Ev3DevFs.readFloat(this.getSensorPath(3));
      sample[4] = Ev3DevFs.readFloat(this.getSensorPath(4));
    }
  }

  private class ColorCodeMode
      extends SensorMode {

    private ColorCodeMode(final File devicePath) {
      super(ModeType.COLOR_CODE, devicePath, VALUE0, VALUE1, VALUE2, VALUE3, VALUE4, VALUE5);
    }

    @Override
    public int sampleSize() { return 6; }

    @Override
    public void fetchSample(final float[] sample, final int offset) {
      switchMode(this.getModeType());
      sample[0] = Ev3DevFs.readFloat(this.getSensorPath(0));
      sample[1] = Ev3DevFs.readFloat(this.getSensorPath(1));
      sample[2] = Ev3DevFs.readFloat(this.getSensorPath(2));
      sample[3] = Ev3DevFs.readFloat(this.getSensorPath(3));
      sample[4] = Ev3DevFs.readFloat(this.getSensorPath(4));
      sample[5] = Ev3DevFs.readFloat(this.getSensorPath(5));
    }
  }

  private class AngleMode
      extends SensorMode {

    private AngleMode(final File devicePath) {
      super(ModeType.ANGLE, devicePath, VALUE0);
    }

    @Override
    public void fetchSample(final float[] sample, final int offset) {
      switchMode(this.getModeType());
      sample[0] = Ev3DevFs.readFloat(this.getSensorPath(offset));
    }
  }
}
