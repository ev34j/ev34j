package com.ev34j.core.sensor;

enum ModuleType {

  EV3_TOUCH("lego-ev3-touch"),
  EV3_COLOR("lego-ev3-color"),
  NXT1_COLOR("ht-nxt-color"),
  NXT2_COLOR("ht-nxt-color-v2"),
  EV3_GYRO("lego-ev3-gyro"),
  NXT_GYRO("ht-nxt-gyro"),
  EV3_IR("lego-ev3-ir"),
  EV3_US("lego-ev3-us"),
  NXT_TOUCH("lego-nxt-touch"),
  NXT_SOUND("lego-nxt-sound"),
  NXT_US("lego-nxt-us"),
  NXT_I2C_SENSOR("nxt-i2c-sensor");

  private final String type;

  ModuleType(final String type) {
    this.type = type;
  }

  public String getType() { return this.type; }
}
