package com.ev34j.mindstorms.sensor;

import com.ev34j.core.sensor.SensorPort;
import com.ev34j.core.sensor.SensorSetting;
import com.ev34j.core.sensor.UltrasonicSensor;

public class NxtUltrasonicSensor
    extends AbstractUltrasonicSensor {

  public NxtUltrasonicSensor(final int portNum) {
    this("" + portNum);
  }

  public NxtUltrasonicSensor(final String portName) {
    super((new UltrasonicSensor(NxtUltrasonicSensor.class, SensorPort.findByPort(portName), SensorSetting.NXT_US)));
  }
}
