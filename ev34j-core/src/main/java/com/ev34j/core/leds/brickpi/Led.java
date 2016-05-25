package com.ev34j.core.leds.brickpi;

import com.ev34j.core.utils.Ev3DevFs;

import static com.ev34j.core.common.AttributeName.LEDS;
import static com.ev34j.core.leds.ev3.Led.BRIGHTNESS;
import static java.lang.String.format;

public class Led {

  private final String path;

  Led(final LedType ledType) {
    this.path = format("%s/%s/%s", LEDS.getPath(), ledType.getPath(), BRIGHTNESS);
  }

  private void setValue(final int value) { Ev3DevFs.write(this.path, value); }

  public boolean isOn() { return Ev3DevFs.readInteger(this.path) != 0; }

  public void on() { this.setValue(255); }

  public void off() { this.setValue(0); }
}
