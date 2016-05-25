package com.ev34j.core.leds.ev3;

import java.util.concurrent.atomic.AtomicReference;

public class Leds {

  private static final AtomicReference<Leds> SINGLETON = new AtomicReference<>();

  public static Leds getInstance() {
    if (SINGLETON.get() == null)
      SINGLETON.compareAndSet(null, new Leds());
    return SINGLETON.get();
  }

  private final Led left  = new Led(LedType.LEFT);
  private final Led right = new Led(LedType.RIGHT);

  private Leds() { }

  public Led left() { return this.left; }

  public Led right() { return this.right; }
}
