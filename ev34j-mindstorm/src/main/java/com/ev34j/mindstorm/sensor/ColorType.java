package com.ev34j.mindstorm.sensor;

public enum ColorType {

  NONE(0),
  BLACK(1),
  BLUE(2),
  GREEN(3),
  YELLOW(4),
  RED(5),
  WHITE(6),
  BROWN(7);

  private final int value;

  ColorType(final int value) {
    this.value = value;
  }

  public int getValue() { return this.value; }

  public static ColorType findByValue(final int value) {
    for (final ColorType colorType : values()) {
      if (colorType.getValue() == value)
        return colorType;
    }
    return NONE;
  }
}
