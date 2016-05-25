package com.ev34j.core.buttons.ev3;

import static java.lang.String.format;

public enum ButtonType {

  UP(103),
  DOWN(108),
  LEFT(105),
  RIGHT(106),
  ENTER(28),
  BACKSPACE(14);

  private final int value;

  ButtonType(final int value) {
    this.value = value;
  }

  public int getValue() { return this.value; }

  public static ButtonType findByValue(final int value) {
    for (ButtonType type : ButtonType.values())
      if (type.getValue() == value)
        return type;
    throw new IllegalArgumentException(format("Invalid %s value: %d", ButtonType.class.getSimpleName(), value));
  }
}
