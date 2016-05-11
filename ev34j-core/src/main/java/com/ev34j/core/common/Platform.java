package com.ev34j.core.common;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

import static com.ev34j.core.common.AttributeName.POWER_SUPPLY;
import static java.lang.String.format;

/**
 * This class been designed to discover if the library is used in:
 * <p>
 * - EV3 Brick
 * - Raspberry Pi 1 + PiStorms
 * - Raspberry Pi 1 + BrickPi
 *
 * @author Juan Antonio Breña Moral
 */
public class Platform {

  private static final Logger                        LOGGER   = Logger.getLogger(Platform.class.getName());
  private static final AtomicReference<PlatformType> PLATFORM = new AtomicReference<>(null);

  /**
   * This method returns the platform
   *
   * @return
   * @throws RuntimeException
   */
  public static PlatformType getPlatform() {
    // Double Latch
    if (PLATFORM.get() == null) {
      synchronized (PLATFORM) {
        if (PLATFORM.get() == null) {
          for (final PlatformType type : PlatformType.values()) {
            final String dirName = format("%s/%s", POWER_SUPPLY.getPath(), type.getBattery());
            final File f = new File(dirName);
            if (f.exists() && f.isDirectory()) {
              LOGGER.fine(format("Detected the platform: %s", type.getType()));
              PLATFORM.set(type);
              break;
            }
          }
        }
      }
    }

    if (PLATFORM.get() == null)
      throw new RuntimeException("Platform not supported");
    return PLATFORM.get();
  }

  public static boolean isRasPiDevice() { return isBrickPi() || isPiStorm(); }

  public static boolean isEv3Brick() { return getPlatform() == PlatformType.EV3BRICK; }

  public static boolean isBrickPi() { return getPlatform() == PlatformType.BRICKPI; }

  public static boolean isPiStorm() { return getPlatform() == PlatformType.PISTORMS; }
}
