package com.ev34j.mindstorm.leds;

import com.ev34j.core.leds.ev3.Led;

public class Ev3StatusLight {

  private final Led led;

  Ev3StatusLight(final Led led) {
    this.led = led;
  }

  public void rgValues(final int redValue, final int greenValue) { this.led.rgValues(redValue, greenValue); }

  public void red() { this.led.red(); }

  public void green() { this.led.green(); }

  public void orange() { this.led.orange(); }

  public void off() { this.led.off(); }
}
