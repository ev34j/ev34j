package com.ev34j.mindstorms.sensor;

import static java.lang.String.format;

public class RgbValue {

  private final int redValue;
  private final int greenValue;
  private final int blueValue;

  public RgbValue(final int redValue, final int greenValue, final int blueValue) {
    this.redValue = redValue;
    this.greenValue = greenValue;
    this.blueValue = blueValue;
  }

  public int getRedValue() { return this.redValue; }

  public int getGreenValue() { return this.greenValue; }

  public int getBlueValue() { return this.blueValue; }

  @Override
  public String toString() {
    return format("Red: %d Green: %d Blue: %d", this.getRedValue(), this.getGreenValue(), this.getBlueValue());
  }
}
