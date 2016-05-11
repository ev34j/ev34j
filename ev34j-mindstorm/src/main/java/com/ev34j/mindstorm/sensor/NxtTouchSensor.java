package com.ev34j.mindstorm.sensor;

import com.ev34j.core.sensor.SensorSetting;

public class NxtTouchSensor
    extends AbstractTouchSensor {

  public NxtTouchSensor(final int portNum) {
    this("" + portNum);
  }

  public NxtTouchSensor(final String portName) {
    super(NxtTouchSensor.class, portName, SensorSetting.NXT_TOUCH);
  }
}
