package com.ev34j.mindstorms.leds;

import com.ev34j.core.leds.ev3.Leds;
import com.ev34j.core.system.DeviceNotSupportedException;
import com.ev34j.core.system.Platform;

public class Ev3StatusLights {

  private static final Ev3StatusLight LEFT  = new Ev3StatusLight(Leds.getInstance().left());
  private static final Ev3StatusLight RIGHT = new Ev3StatusLight(Leds.getInstance().right());

  static {
    if (!Platform.isEv3Brick())
      throw new DeviceNotSupportedException(Ev3StatusLights.class);
  }

  private Ev3StatusLights() { }

  public static Ev3StatusLight left() { return LEFT; }

  public static Ev3StatusLight right() { return RIGHT; }
}
