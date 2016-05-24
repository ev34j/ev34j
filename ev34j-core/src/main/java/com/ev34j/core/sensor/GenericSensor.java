package com.ev34j.core.sensor;

import com.ev34j.core.common.Device;
import com.ev34j.core.common.DeviceException;
import com.ev34j.core.common.Platform;
import com.ev34j.core.motor.PortType;
import com.ev34j.core.utils.Delay;
import com.ev34j.core.utils.Ev3DevFs;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

import static com.ev34j.core.common.AttributeName.LEGO_PORT_MODE;
import static com.ev34j.core.common.AttributeName.LEGO_SENSOR_MODE;
import static com.ev34j.core.common.AttributeName.SET_DEVICE;
import static com.ev34j.core.motor.PortType.LEGO_PORT;
import static com.ev34j.core.motor.PortType.LEGO_SENSOR;
import static com.ev34j.core.sensor.DriverType.NONE;
import static java.lang.String.format;

/**
 * Base class to interact with EV3Dev Sensors
 *
 * @author Juan Antonio Breña Moral
 */
public abstract class GenericSensor
    extends Device {

  private static final Logger  LOGGER              = Logger.getLogger(GenericSensor.class.getName());
  private static final long    SWITCH_DELAY_MILLIS = 200;
  private static final boolean RESET_SENSOR_PORTS  = true;

  private final AtomicBoolean             initialized = new AtomicBoolean(false);
  private final Map<ModeType, SensorMode> modeTypeMap = new HashMap<>();
  private final AtomicReference<ModeType> modeType    = new AtomicReference<>();

  static {
    // Executed only once
    if (RESET_SENSOR_PORTS && Platform.isRasPiDevice()) {
      // System.out.println("Begin port reset");
      resetAllSensorPorts();
      // System.out.println("End port reset");
    }
  }

  private static void resetAllSensorPorts() {
    for (File path : Ev3DevFs.getDevicePaths(LEGO_PORT)) {
      final String addressPath = format("%s/%s", path, ADDRESS);
      final String val = Ev3DevFs.readString(addressPath);
      // Check if it is a motor -- only reset sensor ports
      if (SensorPort.isSensorAddress(val)) {
        Ev3DevFs.write(format("%s/%s", path, LEGO_PORT_MODE.getAttribName()), NONE.getType());
        // Delay.delayMillis(GenericSensor.SWITCH_DELAY_MILLIS);
      }
    }
  }

  /**
   * Every device connected in a EV3 Brick with EV3Dev appears in /sys/class in a determinated category.
   * It is necessary to indicate the type and port.
   *
   * @param sensorPort   The port where is connected the sensor or the actuator.
   * @param autoDetected
   * @throws DeviceException
   */
  protected GenericSensor(final Class<?> deviceClass,
                          final SensorPort sensorPort,
                          final DriverType driverType,
                          final ModuleType moduleType,
                          final boolean autoDetected)
      throws DeviceException {
    // EV3 detects the sensors automatically
    if (Platform.isEv3Brick() && autoDetected) {
      this.detectDevice(LEGO_SENSOR, deviceClass, sensorPort);
      LOGGER.fine(format("Detected sensor at %s", this.getDevicePath()));
    }
    else {
      // With Pi Boards, it is necessary to detect the sensors in 2 paths
      this.detectDevice(LEGO_PORT, deviceClass, sensorPort);
      LOGGER.fine(format("Detected sensor at %s", this.getDevicePath()));

      // Create the dir in /sys/class/lego-sensor with these calls -- the order here matters
      this.setAttribute(LEGO_PORT_MODE, driverType.getType());
      this.setAttribute(SET_DEVICE, moduleType.getType());
      Delay.millis(SWITCH_DELAY_MILLIS);

      // Make sure dir was created
      this.detectDevice(LEGO_SENSOR, deviceClass, sensorPort);
      LOGGER.fine(format("Created sensor at %s", this.getDevicePath()));
    }
  }

  private void setModeType(final ModeType modeType) { this.modeType.set(modeType); }

  protected ModeType getModeType() { return this.modeType.get(); }

  protected SensorMode getSensorMode(final ModeType modeType) {
    final SensorMode sensorMode = this.modeTypeMap.get(modeType);
    if (sensorMode == null)
      throw new IllegalArgumentException(format("Invalid mode: %s", modeType));
    return sensorMode;
  }

  protected void detectDevice(final PortType portType, final Class<?> deviceClass, final SensorPort sensorPort) {
    final File path = this.detectDevicePath(portType, deviceClass, sensorPort.getPortName(), sensorPort.getPortAddress());
    this.setDevicePath(path);
  }

  protected synchronized void assignSensorModes(final SensorMode... sensorModes) {
    if (sensorModes == null || sensorModes.length == 0)
      throw new IllegalArgumentException("At least one SensorMode required");
    this.modeTypeMap.clear();
    for (final SensorMode mode : sensorModes)
      this.modeTypeMap.put(mode.getModeType(), mode);
    this.setModeType(sensorModes[0].getModeType());
  }

  /**
   * Switch to the selected mode (if not already in that mode) and delay for the
   * specified period to allow the sensor to settle in the new mode. <br>
   * A mode of -1 resets the sensor.
   * TODO: There really should be a better way to work out when the switch is
   * complete, if you don't wait though you end up reading data from the previous
   * mode.
   */
  protected void switchMode(final ModeType modeType) {
    if (!this.initialized.get() || modeType.isResetOnSample() || this.getModeType() != modeType) {
      this.setModeType(modeType);
      this.initialized.set(true);
      final String modeValue = this.getModeType().getMode();
      if (modeValue != null) {
        this.setAttribute(LEGO_SENSOR_MODE, modeValue);
        Delay.millis(SWITCH_DELAY_MILLIS);
      }
    }
  }
}
