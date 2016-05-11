package com.ev34j.core.common;

public enum AttributeValue {

  RUN_FOREVER("run-forever"),
  RUN_TIMED("run-timed"),
  RUN_TO_REL_POS("run-to-rel-pos"),
  RUN_TO_ABS_POS("run-to-abs-pos"),
  RUN_DIRECT("run-direct"),
  STOP("stop"),
  RESET("reset"),
  DC_MOTOR("dc-motor"),
  TACHO_MOTOR("tacho-motor"),
  NORMAL_POLARITY("normal"),
  INVERSED_POLARITY("inversed"),
  COAST("coast"),
  BRAKE("brake"),
  HOLD("hold"),
  RUNNING_STATE("running"),
  STALLED_STATE("stalled");

  private final String attribValue;

  AttributeValue(final String attribValue) {
    this.attribValue = attribValue;
  }

  public String attribValue() { return this.attribValue; }
}
