package com.ev34j.core.common;

import static com.ev34j.core.utils.Ev3DevFs.EV3_ROOT;
import static java.lang.String.format;

public enum AttributeName {

  LEGO_SENSOR_MODE("mode"),
  LEGO_PORT_MODE("mode"),
  SET_DEVICE("set_device"),
  COMMAND("command"),
  STATE("state"),
  POWER_SUPPLY("power_supply"),
  VOLTAGE("voltage_now"),
  CURRENT("current_now"),
  POLARITY("polarity"),
  STOP_ACTION("stop_action"),
  FIXED_SPEED("speed_sp"),
  VARIABLE_SPEED("duty_cycle_sp"),
  POWER("power"),
  CURRENT_POSITION("position"),
  POSITION_VAL("position_sp"),
  TIME_TO_RUN_VAL("time_sp"),
  MAX_SPEED("max_speed"),
  LEDS("leds"),
  COUNT_PER_ROT("count_per_rot");

  private final String attribName;
  private final String path;

  AttributeName(final String attribName) {
    this.attribName = attribName;
    this.path = format("%s/%s", EV3_ROOT, this.attribName);
  }

  public String getAttribName() { return this.attribName; }

  public String getPath() { return this.path; }
}
