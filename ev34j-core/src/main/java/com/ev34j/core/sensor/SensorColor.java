package com.ev34j.core.sensor;

public enum SensorColor {

  NONE(-1),
  RED(0),
  GREEN(1),
  BLUE(2),
  YELLOW(3),
  MAGENTA(4),
  ORANGE(5),
  WHITE(6),
  BLACK(7),
  PINK(8),
  GRAY(9),
  LIGHT_GRAY(10),
  DARK_GRAY(11),
  CYAN(12),
  BROWN(13);

  private final int value;

  SensorColor(final int value) {
    this.value = value;
  }

  public int getValue() { return this.value; }
}
