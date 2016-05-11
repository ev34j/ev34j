package com.ev34j.mindstorm.sensor;

import com.ev34j.core.sensor.ColorSensor;
import com.ev34j.core.sensor.SensorPort;
import com.ev34j.core.sensor.SensorSetting;

public class NxtV2ColorSensor
    extends AbstractColorSensor {

  public NxtV2ColorSensor(final int portNum) {
    this("" + portNum);
  }

  public NxtV2ColorSensor(final String portName) {
    super((new ColorSensor(NxtV2ColorSensor.class, SensorPort.findByPort(portName), SensorSetting.NXT2_COLOR)));
  }
}
