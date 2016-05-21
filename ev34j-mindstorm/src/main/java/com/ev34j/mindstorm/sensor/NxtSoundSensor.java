package com.ev34j.mindstorm.sensor;

import com.ev34j.core.sensor.SensorPort;
import com.ev34j.core.sensor.SoundSensor;

import static com.ev34j.core.sensor.SensorSetting.NXT_SOUND;

public class NxtSoundSensor
    extends AbstractSensor<SoundSensor> {

  public NxtSoundSensor(final int portNum) {
    this("" + portNum);
  }

  public NxtSoundSensor(final String portName) {
    super(new SoundSensor(NxtSoundSensor.class,
                          SensorPort.findByPort(portName),
                          NXT_SOUND.getDriverType(),
                          NXT_SOUND.getModuleType()));
  }

  public float getSoundPressureFlatPct() {
    this.setSensorMode(this.getSensor().getSoundPressureFlatMode())
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return this.getSample(0) / 10;
  }

  public float getSoundPressureWeightedPct() {
    this.setSensorMode(this.getSensor().getSoundPressureWeightedMode())
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return this.getSample(0) / 10;
  }
}
