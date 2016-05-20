package com.ev34j.mindstorm.leds;

import com.ev34j.core.leds.BrickPiLed;

public class BrickPiStatusLight {

  private final BrickPiLed led;

  BrickPiStatusLight(final BrickPiLed led) {
    this.led = led;
  }

  public boolean isOn() { return this.led.isOn(); }

  public void on() { this.led.on(); }

  public void off() { this.led.off(); }
}
