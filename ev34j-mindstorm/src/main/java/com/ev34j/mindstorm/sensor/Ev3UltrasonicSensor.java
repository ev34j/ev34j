package com.ev34j.mindstorm.sensor;

import com.ev34j.core.sensor.SensorPort;
import com.ev34j.core.sensor.SensorSetting;
import com.ev34j.core.sensor.UltrasonicSensor;

public class Ev3UltrasonicSensor
    extends AbstractUltrasonicSensor {

  public Ev3UltrasonicSensor(final int portNum) {
    this("" + portNum);
  }

  public Ev3UltrasonicSensor(final String portName) {
    super((new UltrasonicSensor(Ev3UltrasonicSensor.class, SensorPort.findByPort(portName), SensorSetting.EV3_US)));
  }
}
