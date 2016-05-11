package com.ev34j.core.sensor;

public enum SensorSetting {

  // Touch
  NXT_TOUCH(ConnnectionType.NXT_ANALOG, SensorType.NXT_TOUCH),
  EV3_TOUCH(ConnnectionType.EV3_ANALOG, SensorType.EV3_TOUCH),

  // Ultrasonic
  NXT_US(ConnnectionType.NXT_IC2, SensorType.NXT_US),
  EV3_US(ConnnectionType.EV3_UART, SensorType.EV3_US),

  // Color
  NXT1_COLOR(ConnnectionType.NXT_IC2, SensorType.NXT1_COLOR),
  NXT2_COLOR(ConnnectionType.NXT_IC2, SensorType.NXT2_COLOR),
  EV3_COLOR(ConnnectionType.EV3_UART, SensorType.EV3_COLOR),

  // Gyro
  NXT_GYRO(ConnnectionType.NXT_ANALOG, SensorType.NXT_GYRO),
  EV3_GYRO(ConnnectionType.EV3_UART, SensorType.EV3_GYRO);

  private final ConnnectionType connnectionType;
  private final SensorType      sensorType;

  SensorSetting(final ConnnectionType connnectionType, final SensorType sensorType) {
    this.connnectionType = connnectionType;
    this.sensorType = sensorType;
  }

  public ConnnectionType getConnnectionType() { return this.connnectionType; }

  public SensorType getSensorType() { return this.sensorType; }
}
