package com.ev34j.mindstorm.sensor;

import com.ev34j.core.sensor.SensorPort;
import com.ev34j.core.sensor.SensorSetting;
import com.ev34j.core.sensor.ev3.GyroSensor;

public class Ev3GyroSensor
    extends AbstractSensor<GyroSensor> {

  public Ev3GyroSensor(final int portNum) {
    this("" + portNum);
  }

  public Ev3GyroSensor(final String portName) {
    super((new GyroSensor(Ev3GyroSensor.class, SensorPort.findByPort(portName), SensorSetting.EV3_GYRO)));
  }

  public void reset() { this.getSensor().reset(); }

  public int getAngleDegrees() {
    this.setSensorMode(this.getSensor().getAngleDegreesMode())
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return (int) this.getSample(0);
  }

  public int getRotationalSpeed() {
    this.setSensorMode(this.getSensor().getRotationalSpeedMode())
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return (int) this.getSample(0);
  }
}
