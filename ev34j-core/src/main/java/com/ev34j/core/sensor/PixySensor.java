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

public class PixySensor
    extends GenericSensor {

  private final ModeType[]       sigModeTypes   = {SIG1, SIG2, SIG3, SIG4, SIG5, SIG6, SIG7};
  private final List<SensorMode> sigSensorModes = new ArrayList<>();

  public PixySensor(final Class<?> deviceClass,
                    final SensorPort sensorPort,
                    final DriverType driverType,
                    final ModuleType moduleType) {
    super(deviceClass, sensorPort, driverType, moduleType, true);
    for (ModeType modeType : this.sigModeTypes)
      this.sigSensorModes.add(new SingleSignatureMode(modeType, this.getDevicePath()));
    this.assignSensorModes(this.sigSensorModes.toArray(new SensorMode[sigModeTypes.length]));
  }

  public SensorMode getSigMode(final int pos) { return this.getSensorMode(sigModeTypes[pos]); }

  private class SingleSignatureMode
      extends SensorMode {

    private SingleSignatureMode(final ModeType modeType, final File devicePath) {
      super(modeType, devicePath, VALUE0, VALUE1, VALUE2, VALUE3, VALUE4);
    }

    @Override
    public void fetchSample(final float[] sample, final int offset) {
      switchMode(this.getModeType());
      sample[0] = Ev3DevFs.readFloat(this.getSensorPath(offset));
    }
  }
}
