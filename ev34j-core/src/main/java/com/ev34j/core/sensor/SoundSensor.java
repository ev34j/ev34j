package com.ev34j.core.sensor;

import com.ev34j.core.utils.Ev3DevFs;

import java.io.File;

import static com.ev34j.core.sensor.ModeType.SOUND_PRESSURE_FLAT;
import static com.ev34j.core.sensor.ModeType.SOUND_PRESSURE_WEIGHTED;
import static com.ev34j.core.sensor.SensorValue.VALUE0;

public class SoundSensor
    extends GenericSensor {

  public SoundSensor(final Class<?> deviceClass,
                     final SensorPort sensorPort,
                     final DriverType driverType,
                     final ModuleType moduleType) {
    super(deviceClass, sensorPort, driverType, moduleType, false);
    // performPortSetup required for sound sensor: http://www.ev3dev.org/docs/sensors/#fn:nxt-analog
    this.assignSensorModes(new SoundPressureFlatMode(this.getDevicePath()),
                           new SoundPressureWeightedMode(this.getDevicePath()));
  }

  public SensorMode getSoundPressureFlatMode() { return this.getSensorMode(SOUND_PRESSURE_FLAT); }

  public SensorMode getSoundPressureWeightedMode() { return this.getSensorMode(SOUND_PRESSURE_WEIGHTED); }

  private class SoundPressureFlatMode
      extends SensorMode {

    private SoundPressureFlatMode(final File devicePath) {
      super(SOUND_PRESSURE_FLAT, devicePath, VALUE0);
    }

    @Override
    public void fetchSample(final float[] sample, final int offset) {
      switchMode(this.getModeType());
      sample[0] = Ev3DevFs.readFloat(this.getSensorPath(offset));
    }
  }

  private class SoundPressureWeightedMode
      extends SensorMode {

    private SoundPressureWeightedMode(final File devicePath) {
      super(SOUND_PRESSURE_WEIGHTED, devicePath, VALUE0);
    }

    @Override
    public void fetchSample(final float[] sample, final int offset) {
      switchMode(this.getModeType());
      sample[0] = Ev3DevFs.readFloat(this.getSensorPath(offset));
    }
  }
}
