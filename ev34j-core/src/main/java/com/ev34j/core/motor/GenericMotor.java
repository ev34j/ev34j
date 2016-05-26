package com.ev34j.core.motor;

import com.ev34j.core.common.AttributeValue;
import com.ev34j.core.common.Device;
import com.ev34j.core.common.PlatformType;
import com.ev34j.core.system.DeviceException;
import com.ev34j.core.system.DeviceNotSupportedException;
import com.ev34j.core.system.Platform;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import static com.ev34j.core.common.AttributeName.COMMAND;

/**
 * Base class to interact with EV3Dev sysfs
 *
 * @author Juan Antonio Breña Moral
 */
public abstract class GenericMotor
    extends Device {

  private static final List<PlatformType> ALL_DEVICES = Arrays.asList(PlatformType.values());

  /**
   * Every device connected in a EV3 Brick with EV3Dev appears in /sys/class in a determinated category.
   * It is necessary to indicate the type and port.
   *
   * @param portType  A valid type. Example: tacho-motor, lego-sensor, etc...
   * @param motorPort The port where is connected the sensor or the actuator.
   * @throws DeviceException
   */
  protected GenericMotor(final Class<?> deviceClass, final PortType portType, final MotorPort motorPort)
      throws DeviceException {
    // This method is oriented for EV3Brick, but for Pi Boards, it is necessary to detect in a previous action
    this(deviceClass, portType, motorPort, ALL_DEVICES);
  }

  /**
   * Every device connected in a EV3 Brick with EV3Dev appears in /sys/class in a determinated category.
   * It is necessary to indicate the type and port.
   * <p>
   * This constructor add the way to detect if some device is not allowed for some platform.
   * Example: DC Motors in Pi Boards.
   *
   * @param portType
   * @param motorPort
   * @param supportedPlatforms
   * @throws DeviceException
   * @throws DeviceNotSupportedException
   */
  protected GenericMotor(final Class<?> deviceClass,
                         final PortType portType,
                         final MotorPort motorPort,
                         final List<PlatformType> supportedPlatforms)
      throws DeviceException, DeviceNotSupportedException {
    if (!supportedPlatforms.contains(Platform.getPlatform()))
      throw new DeviceNotSupportedException(this.getClass());
    final File path = this.detectDevicePath(portType, deviceClass, motorPort.getPortName(), motorPort.getPortAddress());
    this.setDevicePath(path);
  }

  protected void command(final AttributeValue value) { this.setAttribute(COMMAND, value); }
}
