package com.ev34j.mindstorms.battery;

import com.ev34j.core.battery.Battery;

public class Ev3Battery {

  private Ev3Battery() { }

  public static float getVoltage() { return Battery.getInstance().getVoltage(); }

  public static float getBatteryCurrent() { return Battery.getInstance().getBatteryCurrent(); }
}
