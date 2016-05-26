package com.ev34j.mindstorms.leds;

import com.ev34j.core.leds.brickpi.Led;

public class BrickPiStatusLight {

  private final Led led;

  BrickPiStatusLight(final Led led) {
    this.led = led;
  }

  public boolean isOn() { return this.led.isOn(); }

  public void on() { this.led.on(); }

  public void off() { this.led.off(); }
}
