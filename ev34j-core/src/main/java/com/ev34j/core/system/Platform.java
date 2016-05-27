package com.ev34j.core.system;

import com.ev34j.core.common.PlatformType;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

import static com.ev34j.core.common.AttributeName.POWER_SUPPLY;
import static java.lang.String.format;

/**
 * Provides current execution enviroment information
 *
 * @author Juan Antonio Breña Moral
 */
public class Platform {

  private static final Logger                        LOGGER   = Logger.getLogger(Platform.class.getName());
  private static final AtomicReference<PlatformType> PLATFORM = new AtomicReference<>(null);

  public static PlatformType getPlatform() {
    if (PLATFORM.get() == null)
      PLATFORM.compareAndSet(null, lookupType());
    return PLATFORM.get();
  }

  private static PlatformType lookupType() {
    for (final PlatformType type : PlatformType.values()) {
      final String dirName = format("%s/%s", POWER_SUPPLY.getPath(), type.getBattery());
      final File f = new File(dirName);
      if (f.exists() && f.isDirectory()) {
        LOGGER.fine(format("Detected the platform: %s", type.name()));
        return type;
      }
    }
    LOGGER.fine("Unknown platform");
    return PlatformType.UNKNOWN;
  }

  public static boolean isRasPiDevice() { return isBrickPi() || isPiStorm(); }

  public static boolean isEv3Brick() { return getPlatform() == PlatformType.EV3BRICK; }

  public static boolean isBrickPi() { return getPlatform() == PlatformType.BRICKPI; }

  public static boolean isPiStorm() { return getPlatform() == PlatformType.PISTORMS; }

  public static boolean isUnknown() { return getPlatform() == PlatformType.UNKNOWN; }
}
