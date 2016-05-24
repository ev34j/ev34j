package com.ev34j.core.sensor;

public enum SensorSetting {

  // Touch
  NXT_TOUCH(DriverType.NXT_ANALOG, ModuleType.NXT_TOUCH),
  EV3_TOUCH(DriverType.EV3_ANALOG, ModuleType.EV3_TOUCH),

  // Sound
  NXT_SOUND(DriverType.NXT_ANALOG, ModuleType.NXT_SOUND),

  // Pixy
  PIXY_SENSOR(DriverType.PIXY, ModuleType.NXT_I2C_SENSOR),

  // Ultrasonic
  NXT_US(DriverType.NXT_IC2, ModuleType.NXT_US),
  EV3_US(DriverType.EV3_UART, ModuleType.EV3_US),

  // Color
  NXT1_COLOR(DriverType.NXT_IC2, ModuleType.NXT1_COLOR),
  NXT2_COLOR(DriverType.NXT_IC2, ModuleType.NXT2_COLOR),
  EV3_COLOR(DriverType.EV3_UART, ModuleType.EV3_COLOR),

  // Gyro
  NXT_GYRO(DriverType.NXT_ANALOG, ModuleType.NXT_GYRO),
  EV3_GYRO(DriverType.EV3_UART, ModuleType.EV3_GYRO);

  private final DriverType driverType;
  private final ModuleType moduleType;

  SensorSetting(final DriverType driverType, final ModuleType moduleType) {
    this.driverType = driverType;
    this.moduleType = moduleType;
  }

  public DriverType getDriverType() { return this.driverType; }

  public ModuleType getModuleType() { return this.moduleType; }
}
