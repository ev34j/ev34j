package com.ev34j.core.motor;

import static com.ev34j.core.utils.Ev3DevFs.EV3_ROOT;
import static java.lang.String.format;

public enum PortType {

  LEGO_PORT("lego-port"),
  LEGO_SENSOR("lego-sensor"),
  TACHO_MOTOR("tacho-motor"),
  DC_MOTOR("dc-motor");

  private final String type;
  private final String path;

  PortType(final String type) {
    this.type = type;
    this.path = format("%s/%s", EV3_ROOT, this.getType());
  }

  public String getType() { return this.type; }

  public String getPath() { return this.path; }
}
