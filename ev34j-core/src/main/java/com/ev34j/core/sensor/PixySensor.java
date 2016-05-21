package com.ev34j.core.sensor;

import com.ev34j.core.utils.Ev3DevFs;

import java.io.File;

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

public class PixySensor
    extends GenericSensor {

  public PixySensor(final Class<?> deviceClass,
                    final SensorPort sensorPort,
                    final DriverType driverType,
                    final ModuleType moduleType) {
    super(deviceClass, sensorPort, driverType, moduleType, true);
    this.assignModes(new Sig1Mode(this.getDevicePath()),
                     new Sig2Mode(this.getDevicePath()),
                     new Sig3Mode(this.getDevicePath()),
                     new Sig4Mode(this.getDevicePath()));
  }

  public SensorMode getSig1Mode() { return this.getSensorMode(SIG1); }

  public SensorMode getSig2Mode() { return this.getSensorMode(SIG2); }

  public SensorMode getSig3Mode() { return this.getSensorMode(SIG3); }

  public SensorMode getSig4Mode() { return this.getSensorMode(SIG4); }

  public SensorMode getSig5Mode() { return this.getSensorMode(SIG5); }

  public SensorMode getSig6Mode() { return this.getSensorMode(SIG6); }

  public SensorMode getSig7Mode() { return this.getSensorMode(SIG7); }

  private abstract class AbstractSingleSignatureMode
      extends SensorMode {
    private AbstractSingleSignatureMode(final ModeType modeType, final File devicePath) {
      super(modeType, devicePath, VALUE0, VALUE1, VALUE2, VALUE3, VALUE4);
    }

    @Override
    public int sampleSize() { return 5; }

    @Override
    public void fetchSample(final float[] sample, final int offset) {
      switchMode(this.getModeType());
      sample[offset] = Ev3DevFs.readFloat(this.getSensorPath(0));
      sample[offset + 1] = Ev3DevFs.readFloat(this.getSensorPath(1));
      sample[offset + 2] = Ev3DevFs.readFloat(this.getSensorPath(2));
      sample[offset + 3] = Ev3DevFs.readFloat(this.getSensorPath(3));
      sample[offset + 4] = Ev3DevFs.readFloat(this.getSensorPath(4));
    }
  }

  private class Sig1Mode
      extends AbstractSingleSignatureMode {
    private Sig1Mode(final File devicePath) {
      super(SIG1, devicePath);
    }
  }

  private class Sig2Mode
      extends AbstractSingleSignatureMode {
    private Sig2Mode(final File devicePath) {
      super(SIG2, devicePath);
    }
  }

  private class Sig3Mode
      extends AbstractSingleSignatureMode {
    private Sig3Mode(final File devicePath) {
      super(SIG3, devicePath);
    }
  }

  private class Sig4Mode
      extends AbstractSingleSignatureMode {
    private Sig4Mode(final File devicePath) {
      super(SIG4, devicePath);
    }
  }

  private class Sig5Mode
      extends AbstractSingleSignatureMode {
    private Sig5Mode(final File devicePath) {
      super(SIG5, devicePath);
    }
  }

  private class Sig6Mode
      extends AbstractSingleSignatureMode {
    private Sig6Mode(final File devicePath) {
      super(SIG6, devicePath);
    }
  }

  private class Sig7Mode
      extends AbstractSingleSignatureMode {
    private Sig7Mode(final File devicePath) {
      super(SIG7, devicePath);
    }
  }
}
