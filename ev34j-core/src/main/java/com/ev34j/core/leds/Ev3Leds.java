package com.ev34j.core.leds;

import java.util.concurrent.atomic.AtomicReference;

public class Ev3Leds {

  private static final AtomicReference<Ev3Leds> SINGLETON = new AtomicReference<>();

  public static Ev3Leds getInstance() {
    if (SINGLETON.get() == null)
      SINGLETON.compareAndSet(null, new Ev3Leds());
    return SINGLETON.get();
  }

  private final Ev3Led left  = new Ev3Led(Ev3LedType.LEFT);
  private final Ev3Led right = new Ev3Led(Ev3LedType.RIGHT);

  private Ev3Leds() { }

  public Ev3Led left() { return this.left; }

  public Ev3Led right() { return this.right; }
}
