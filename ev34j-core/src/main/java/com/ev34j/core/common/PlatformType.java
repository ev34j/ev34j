package com.ev34j.core.common;

public enum PlatformType {

  EV3BRICK("legoev3-battery"),
  PISTORMS("pistorm-battery"),
  BRICKPI("brickpi-battery"),
  UNKNOWN("none");

  private final String battery;

  PlatformType(final String battery) {
    this.battery = battery;
  }

  public String getBattery() { return this.battery; }
}
