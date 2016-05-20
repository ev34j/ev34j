package com.ev34j.core.leds;

public enum Ev3LedType {
  LEFT("ev3:left:green:ev3dev", "ev3:left:red:ev3dev"),
  RIGHT("ev3:right:green:ev3dev", "ev3:right:red:ev3dev");

  private final String green;
  private final String red;

  Ev3LedType(final String green, final String red) {
    this.green = green;
    this.red = red;
  }

  public String getGreen() { return this.green; }

  public String getRed() { return this.red; }
}
