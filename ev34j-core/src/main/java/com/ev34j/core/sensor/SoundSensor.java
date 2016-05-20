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
                     final ConnnectionType connnectionType,
                     final SensorType sensorType) {
    super(deviceClass, sensorPort, connnectionType, sensorType, true);
    // performPortSetup required for sound sensor: http://www.ev3dev.org/docs/sensors/#fn:nxt-analog
    this.assignModes(new SoundPressureFlatMode(this.getDevicePath()),
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
      sample[offset] = Ev3DevFs.readFloat(this.getSensorPath(0));
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
      sample[offset] = Ev3DevFs.readFloat(this.getSensorPath(0));
    }
  }
}
