package com.ev34j.core.leds;

import com.ev34j.core.utils.Ev3DevFs;

import static com.ev34j.core.common.AttributeName.LEDS;
import static com.ev34j.core.leds.Ev3Led.BRIGHTNESS;
import static java.lang.String.format;

public class BrickPiLed {

  private final String path;

  BrickPiLed(final BrickPiLedType ledType) {
    this.path = format("%s/%s/%s", LEDS.getPath(), ledType.getPath(), BRIGHTNESS);
  }

  private void setValue(final int value) { Ev3DevFs.write(this.path, value); }

  public boolean isOn() { return Ev3DevFs.readInteger(this.path) != 0; }

  public void on() { this.setValue(255); }

  public void off() { this.setValue(0); }
}
