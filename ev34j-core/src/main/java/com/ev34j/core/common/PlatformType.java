package com.ev34j.core.common;

public enum PlatformType {

  EV3BRICK("EV3BRICK", "legoev3-battery"),
  PISTORMS("PISTORMS", "pistorm-battery"),
  BRICKPI("BRICKPI", "brickpi-battery");

  private final String type;
  private final String battery;

  PlatformType(final String type, final String battery) {
    this.type = type;
    this.battery = battery;
  }

  public String getType() { return this.type; }

  public String getBattery() { return this.battery; }
}
