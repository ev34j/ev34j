package com.ev34j.core.sensor;

public enum DriverType {

  NONE("none"),
  NXT_ANALOG("nxt-analog"),
  NXT_COLOR("nxt-color"),
  NXT_IC2("nxt-i2c"),
  EV3_ANALOG("ev3-analog"),
  EV3_UART("ev3-uart"),
  PIXY("pixy-lego");

  private final String type;

  DriverType(final String type) {
    this.type = type;
  }

  public String getType() { return this.type; }
}
