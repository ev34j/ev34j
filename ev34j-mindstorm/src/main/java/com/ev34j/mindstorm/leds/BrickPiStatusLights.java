package com.ev34j.mindstorm.leds;

import com.ev34j.core.common.DeviceNotSupportedException;
import com.ev34j.core.common.Platform;
import com.ev34j.core.leds.BrickPiLeds;

public class BrickPiStatusLights {

  private static final BrickPiStatusLight LEFT  = new BrickPiStatusLight(BrickPiLeds.getInstance().left());
  private static final BrickPiStatusLight RIGHT = new BrickPiStatusLight(BrickPiLeds.getInstance().right());

  static {
    if (!Platform.isBrickPi())
      throw new DeviceNotSupportedException(BrickPiStatusLights.class);
  }

  private BrickPiStatusLights() {}

  public static BrickPiStatusLight left() { return LEFT; }

  public static BrickPiStatusLight right() { return RIGHT; }
}
