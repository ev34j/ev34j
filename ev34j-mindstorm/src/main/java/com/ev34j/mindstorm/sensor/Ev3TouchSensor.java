package com.ev34j.mindstorm.sensor;

import com.ev34j.core.sensor.SensorSetting;

public class Ev3TouchSensor
    extends AbstractTouchSensor {

  public Ev3TouchSensor(final int portNum) {
    this("" + portNum);
  }

  public Ev3TouchSensor(final String portName) {
    super(Ev3TouchSensor.class, portName, SensorSetting.EV3_TOUCH);
  }
}
