package com.ev34j.mindstorms.sensor;

import com.ev34j.core.sensor.ColorSensor;
import com.ev34j.core.sensor.SensorPort;
import com.ev34j.core.sensor.SensorSetting;

public class Ev3ColorSensor
    extends AbstractColorSensor {

  public Ev3ColorSensor(final int portNum) {
    this("" + portNum);
  }

  public Ev3ColorSensor(final String portName) {
    super((new ColorSensor(Ev3ColorSensor.class, SensorPort.findByPort(portName), SensorSetting.EV3_COLOR)));
  }
}
