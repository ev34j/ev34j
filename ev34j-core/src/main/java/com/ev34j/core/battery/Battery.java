package com.ev34j.core.battery;

import com.ev34j.core.common.Device;
import com.ev34j.core.system.Platform;

import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

import static com.ev34j.core.common.AttributeName.CURRENT;
import static com.ev34j.core.common.AttributeName.POWER_SUPPLY;
import static com.ev34j.core.common.AttributeName.VOLTAGE;
import static java.lang.String.format;

/**
 * The class Battery interacts with EV3Dev to get information about battery used.
 *
 * @author Juan Antonio Breña Moral
 * @see <a href="https://www.kernel.org/doc/Documentation/power/power_supply_class.txt">https://www.kernel.org/doc/Documentation/power/power_supply_class.txt</a>
 * @see <a href="https://github.com/ev3dev/ev3dev-lang/blob/develop/wrapper-specification.md#direct-attribute-mappings-5">https://github.com/ev3dev/ev3dev-lang/blob/develop/wrapper-specification.md#direct-attribute-mappings-5</a>
 */
public class Battery
    extends Device {

  private static final AtomicReference<Battery> SINGLETON = new AtomicReference<>();

  public static Battery getInstance() {
    if (SINGLETON.get() == null)
      SINGLETON.compareAndSet(null, new Battery());
    return SINGLETON.get();
  }

  private Battery() {
    final String path = String.format("%s/%s", POWER_SUPPLY.getPath(), Platform.getPlatform().getBattery());
    final File f = new File(path);
    if (!f.exists())
      throw new RuntimeException(format("Invalid Battery path: %s", path));
    this.setDevicePath(f);
  }

  /**
   * Returns voltage of the battery in microvolts.
   *
   * @return voltage
   */
  public float getVoltage() { return this.getIntegerAttribute(VOLTAGE); }

  /**
   * Returns the current of the battery in microamps.
   *
   * @return current
   */
  public float getBatteryCurrent() {
    return Platform.isEv3Brick() ? this.getIntegerAttribute(CURRENT) : -1f;
  }
}
