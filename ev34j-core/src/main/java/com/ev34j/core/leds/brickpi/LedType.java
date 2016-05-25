package com.ev34j.core.leds.brickpi;

public enum LedType {
  LEFT("brickpi:led2:blue:ev3dev"),
  RIGHT("brickpi:led1:blue:ev3dev");

  private final String path;

  LedType(final String path) {
    this.path = path;
  }

  public String getPath() { return this.path; }
}
