package com.ev34j.core.common;

/**
 * Exception thrown when errors are detected in a sensor device state.
 *
 * @author Andy Shaw
 * @author Juan Antonio Brenha Moral
 */
public class DeviceException
    extends RuntimeException {

  private static final long serialVersionUID = -2018195063349111691L;

  public DeviceException(final String message) {
    super(message);
  }

  public DeviceException(final String message, final Throwable cause) {
    super(message, cause);
  }
}
