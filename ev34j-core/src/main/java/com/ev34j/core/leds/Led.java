package com.ev34j.core.leds;

import com.ev34j.core.utils.Ev3DevFs;

import static com.ev34j.core.common.AttributeName.LEDS;
import static java.lang.String.format;

public class Led {

  private static final String BRIGHTNESS     = "brightness" ;
  private static final String MAX_BRIGHTNESS = "max_brightness" ;
  private static final int    MAX_VALUE      = 255;

  private final String redPath;
  private final String greenPath;

  Led(final LedType ledType) {
    this.redPath = format("%s/%s/%s", LEDS.getPath(), ledType.getRed(), BRIGHTNESS);
    this.greenPath = format("%s/%s/%s", LEDS.getPath(), ledType.getGreen(), BRIGHTNESS);
  }

  private static void validateColor(final int value) {
    if (value < 0 || value > MAX_VALUE)
      throw new IllegalArgumentException(format("Invalid color value: %d. Valid values are 0 to %d.", value, MAX_VALUE));
  }

  public void rgValues(final int redValue, final int greenValue) {
    validateColor(redValue);
    validateColor(greenValue);

    Ev3DevFs.write(this.redPath, 0);
    Ev3DevFs.write(this.greenPath, 0);
    Ev3DevFs.write(this.redPath, redValue);
    Ev3DevFs.write(this.greenPath, greenValue);
  }

  public void red() { this.rgValues(255, 0); }

  public void green() { this.rgValues(0, 255); }

  public void orange() { this.rgValues(255, 255); }

  public void off() { this.rgValues(0, 0); }
}
