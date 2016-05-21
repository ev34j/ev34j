package com.ev34j.core.sensor;

public enum SensorValue {

  VALUE0("value0"),
  VALUE1("value1"),
  VALUE2("value2"),
  VALUE3("value3"),
  VALUE4("value4");

  private final String value;

  SensorValue(final String value) {
    this.value = value;
  }

  public String getValue() { return this.value; }
}
