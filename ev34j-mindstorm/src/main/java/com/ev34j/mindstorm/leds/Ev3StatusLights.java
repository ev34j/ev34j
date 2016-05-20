package com.ev34j.mindstorm.leds;

import com.ev34j.core.common.DeviceNotSupportedException;
import com.ev34j.core.common.Platform;
import com.ev34j.core.leds.Ev3Leds;

public class Ev3StatusLights {

  private static final Ev3StatusLight LEFT  = new Ev3StatusLight(Ev3Leds.getInstance().left());
  private static final Ev3StatusLight RIGHT = new Ev3StatusLight(Ev3Leds.getInstance().right());

  static {
    if (!Platform.isEv3Brick())
      throw new DeviceNotSupportedException(Ev3StatusLights.class);
  }

  private Ev3StatusLights() { }

  public static Ev3StatusLight left() { return LEFT; }

  public static Ev3StatusLight right() { return RIGHT; }
}
