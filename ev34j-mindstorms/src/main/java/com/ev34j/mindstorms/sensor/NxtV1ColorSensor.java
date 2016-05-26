package com.ev34j.mindstorms.sensor;

import com.ev34j.core.sensor.ColorSensor;
import com.ev34j.core.sensor.SensorPort;
import com.ev34j.core.sensor.SensorSetting;

public class NxtV1ColorSensor
    extends AbstractColorSensor {

  public NxtV1ColorSensor(final int portNum) {
    this("" + portNum);
  }

  public NxtV1ColorSensor(final String portName) {
    super((new ColorSensor(NxtV1ColorSensor.class, SensorPort.findByPort(portName), SensorSetting.NXT1_COLOR)));
  }
}
