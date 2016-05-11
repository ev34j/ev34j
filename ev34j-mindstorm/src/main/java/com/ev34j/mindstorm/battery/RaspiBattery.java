package com.ev34j.mindstorm.battery;

import com.ev34j.core.battery.Battery;

public class RaspiBattery {

  private RaspiBattery() { }

  public static float getVoltage() { return Battery.getInstance().getVoltage(); }

}
