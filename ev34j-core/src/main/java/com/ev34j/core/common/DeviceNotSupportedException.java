package com.ev34j.core.common;

import static java.lang.String.format;

/**
 * Exception thrown when the device is not supported in a platform.
 *
 * @author Juan Antonio Brenha Moral
 */
public class DeviceNotSupportedException
    extends RuntimeException {

  private static final long serialVersionUID = -643226850460348784L;

  public DeviceNotSupportedException(final Class clazz) {
    super(format("%s is not supported on %s", clazz.getSimpleName(), Platform.getPlatform()));
  }
}
