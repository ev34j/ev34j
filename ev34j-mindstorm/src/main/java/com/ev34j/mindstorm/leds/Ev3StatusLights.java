package com.ev34j.mindstorm.leds;

import com.ev34j.core.leds.Leds;

public class Ev3StatusLights {

  private static final Ev3StatusLight LEFT  = new Ev3StatusLight(Leds.getInstance().left());
  private static final Ev3StatusLight RIGHT = new Ev3StatusLight(Leds.getInstance().right());

  private Ev3StatusLights() { }

  public static Ev3StatusLight left() { return LEFT; }

  public static Ev3StatusLight right() { return RIGHT; }
}
