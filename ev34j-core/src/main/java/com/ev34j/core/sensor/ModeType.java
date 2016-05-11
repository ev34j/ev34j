package com.ev34j.core.sensor;

public enum ModeType {

  // Touch
  TOUCH(null, false),

  // Color
  AMBIENT("COL-AMBIENT", false),
  REFLECTED("COL-REFLECT", false),
  RGB("RGB-RAW", false),
  COLOR_ID("COL-COLOR", false),
  COLOR_RESET("RESET", false),

  // Gyro
  ANGLE_DEGREES("GYRO-ANG", false),
  ROTATIONAL_SPEED("GYRO-RATE", false),
  ANGULAR_SPEED("GYRO", false),

  // Infrared
  IR_DISTANCE("IR-PROX", false),

  // Ultrasonic
  CONT_DISTANCE_CMS("US-DIST-CM", false),
  CONT_DISTANCE_INCHES("US-DIST-IN", false),
  SINGLE_DISTANCE_CMS("US-SI-CM", true),
  SINGLE_DISTANCE_INCHES("US-SI-IN", true),
  US_LISTEN("US-LISTEN", false);


  private final String  mode;
  private final boolean resetOnSample;

  ModeType(final String mode, final boolean resetOnSample) {
    this.mode = mode;
    this.resetOnSample = resetOnSample;
  }

  public String getMode() { return this.mode; }

  public boolean isResetOnSample() { return this.resetOnSample; }
}
