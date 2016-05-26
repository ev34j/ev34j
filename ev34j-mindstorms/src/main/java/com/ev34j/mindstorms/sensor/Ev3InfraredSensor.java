package com.ev34j.mindstorms.sensor;

import com.ev34j.core.sensor.InfraredSensor;
import com.ev34j.core.sensor.SensorPort;

public class Ev3InfraredSensor
    extends AbstractSensor<InfraredSensor> {

  public Ev3InfraredSensor(final int portNum) {
    this("" + portNum);
  }

  public Ev3InfraredSensor(final String portName) {
    super((new InfraredSensor(Ev3InfraredSensor.class, SensorPort.findByPort(portName))));
  }

  public int getDistancePercent() {
    this.setSensorMode(this.getSensor().getDistanceMode())
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return (int) this.getSample(0);
  }
}
