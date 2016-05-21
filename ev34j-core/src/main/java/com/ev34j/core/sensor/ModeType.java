package com.ev34j.core.sensor;

public enum ModeType {

  // Touch
  TOUCH(null),

  // Color
  AMBIENT("COL-AMBIENT"),
  REFLECTED("COL-REFLECT"),
  RGB("RGB-RAW"),
  COLOR_ID("COL-COLOR"),
  COLOR_RESET("RESET"),

  // Gyro
  ANGLE_DEGREES("GYRO-ANG"),
  ROTATIONAL_SPEED("GYRO-RATE"),
  ANGULAR_SPEED("GYRO"),

  // Infrared
  IR_DISTANCE("IR-PROX"),

  // Sound
  SOUND_PRESSURE_FLAT("DB"),
  SOUND_PRESSURE_WEIGHTED("DBA"),

  // Pixy
  ALL_SIGS("ALL"),
  SIG1("SIG1"),
  SIG2("SIG2"),
  SIG3("SIG3"),
  SIG4("SIG4"),
  SIG5("SIG5"),
  SIG6("SIG6"),
  SIG7("SIG7"),
  COLOR_CODE("COL-CODE"),
  ANGLE("ANGLE"),

  // Ultrasonic
  CONT_DISTANCE_CMS("US-DIST-CM"),
  CONT_DISTANCE_INCHES("US-DIST-IN"),
  SINGLE_DISTANCE_CMS("US-SI-CM", true),
  SINGLE_DISTANCE_INCHES("US-SI-IN", true),
  US_LISTEN("US-LISTEN");


  private final String  mode;
  private final boolean resetOnSample;

  ModeType(final String mode) {
    this(mode, false);
  }

  ModeType(final String mode, final boolean resetOnSample) {
    this.mode = mode;
    this.resetOnSample = resetOnSample;
  }

  public String getMode() { return this.mode; }

  public boolean isResetOnSample() { return this.resetOnSample; }
}
