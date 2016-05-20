package com.ev34j.core.leds;

public enum BrickPiLedType {
  LEFT("brickpi:led2:blue:ev3dev"),
  RIGHT("brickpi:led1:blue:ev3dev");

  private final String path;

  BrickPiLedType(final String path) {
    this.path = path;
  }

  public String getPath() { return this.path; }
}
