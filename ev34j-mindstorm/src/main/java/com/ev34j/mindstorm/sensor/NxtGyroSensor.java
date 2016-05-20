package com.ev34j.mindstorm.sensor;

import com.ev34j.core.sensor.GyroSensorNxt;
import com.ev34j.core.sensor.SensorPort;
import com.ev34j.core.sensor.SensorSetting;


public class NxtGyroSensor
    extends AbstractSensor<GyroSensorNxt> {

  public NxtGyroSensor(final int portNum) {
    this("" + portNum);
  }

  public NxtGyroSensor(final String portName) {
    super((new GyroSensorNxt(NxtGyroSensor.class, SensorPort.findByPort(portName), SensorSetting.NXT_GYRO)));
  }

  public int getAngularSpeed() {
    this.setSensorMode(this.getSensor().getAngleSpeedMode())
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return (int) this.getSample(0);
  }
}
