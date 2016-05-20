package com.ev34j.core.leds;

import java.util.concurrent.atomic.AtomicReference;

public class BrickPiLeds {

  private static final AtomicReference<BrickPiLeds> SINGLETON = new AtomicReference<>();

  public static BrickPiLeds getInstance() {
    if (SINGLETON.get() == null)
      SINGLETON.compareAndSet(null, new BrickPiLeds());
    return SINGLETON.get();
  }

  private final BrickPiLed left  = new BrickPiLed(BrickPiLedType.LEFT);
  private final BrickPiLed right = new BrickPiLed(BrickPiLedType.RIGHT);

  private BrickPiLeds() { }

  public BrickPiLed left() { return this.left; }

  public BrickPiLed right() { return this.right; }
}
