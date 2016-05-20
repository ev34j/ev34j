package com.ev34j.core.common;

import com.ev34j.core.motor.PortType;
import com.ev34j.core.utils.Ev3DevFs;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.String.format;

/**
 * Base class for sensor drivers & motors. Provides mechanism to release resources when closed
 *
 * @author Andy Shaw
 * @author Juan Antonio Breña Moral
 */
public abstract class Device
    implements Closeable {

  protected static final String ADDRESS = "address";

  private final AtomicReference<File> devicePathRef = new AtomicReference<>();
  private final List<Closeable>       closeList     = new ArrayList<>();

  protected Device() { }

  /**
   * Add the specified resource to the list of objects that will be closed
   * when the sensor is closed.
   *
   * @param res
   */
  protected void releaseOnClose(final Closeable res) { this.closeList.add(res); }

  protected void setDevicePath(final File path) { this.devicePathRef.set(path); }

  protected File getDevicePath() { return this.devicePathRef.get(); }

  private String getAttributePath(final AttributeName attribute) {
    return format("%s/%s", this.getDevicePath(), attribute.getAttribName());
  }

  /**
   * This method matches a input with the internal position in EV3Dev.
   *
   * @param portType
   * @param portAddress
   */
  protected void detectDevice(final Class<?> deviceClass,
                              final PortType portType,
                              final String portName,
                              final String portAddress) {
    for (final File path : Ev3DevFs.getDevicePaths(portType)) {
      final String addressPath = format("%s/%s", path, ADDRESS);
      final String val = Ev3DevFs.readString(addressPath);
      // NXT Ultrasonic sensor address is in2:i2c1, whereas EV3 IR sensor address is just in2
      if (val.startsWith(portAddress)) {
        this.setDevicePath(path);
        return;
      }
    }
    throw new DeviceException(format("%s not detected on port %s", deviceClass.getSimpleName(), portName));
  }

  /**
   * Close the sensor. Close associated resources.
   */
  @Override
  public void close() {
    for (Closeable res : this.closeList)
      try {
        res.close();
      }
      catch (IOException e) {
        // this should not happen
        throw new DeviceException("Error during close", e);
      }
  }

  protected String getStringAttribute(final AttributeName attribute) {
    return Ev3DevFs.readString(this.getAttributePath(attribute));
  }

  protected int getIntegerAttribute(final AttributeName attribute) {
    return Ev3DevFs.readInteger(this.getAttributePath(attribute));
  }

  protected float getFloatAttribute(final AttributeName attribute) {
    return Ev3DevFs.readFloat(this.getAttributePath(attribute));
  }

  protected void setAttribute(final AttributeName attribute, final AttributeValue value) {
    Ev3DevFs.write(this.getAttributePath(attribute), value.attribValue());
  }

  protected void setAttribute(final AttributeName attribute, final String value) {
    Ev3DevFs.write(this.getAttributePath(attribute), value);
  }

  protected void setAttribute(final AttributeName attribute, final int value) {
    Ev3DevFs.write(this.getAttributePath(attribute), value);
  }

  protected void setAttribute(final AttributeName attribute, final float value) {
    Ev3DevFs.write(this.getAttributePath(attribute), value);
  }
}
