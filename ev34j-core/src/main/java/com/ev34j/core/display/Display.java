package com.ev34j.core.display;

import com.ev34j.core.common.DeviceNotSupportedException;
import com.ev34j.core.common.Platform;
import com.ev34j.core.utils.Ev3DevFs;

import java.util.concurrent.atomic.AtomicReference;

public class Display {
  private final static AtomicReference<Display> SINGLETON = new AtomicReference<>();

  public static Display getInstance() {
    if (SINGLETON.get() == null)
      SINGLETON.compareAndSet(null, new Display());
    return SINGLETON.get();
  }

  private static String FRAMEBUFFER_PATH = "/dev/fb0";
  private static int    SCREEN_WIDTH     = 178;  // pixels
  private static int    SCREEN_HEIGHT    = 128;  // pixels
  private static int    LINE_LENGTH      = 24;   // bytes
  private static int    SIZE             = 3072; // bytes

  private final byte[] screenBuffer = new byte[SIZE];

  private final byte[] originalBuffer;

  private Display() {
    if (!Platform.isEv3Brick())
      throw new DeviceNotSupportedException(this.getClass());
    this.originalBuffer = Ev3DevFs.readBytes(FRAMEBUFFER_PATH);
  }

  public Display inverse() {
    for (int i = 0; i < this.screenBuffer.length; i++)
      this.screenBuffer[i] = (byte) ~this.screenBuffer[i];
    this.writeBuffer(this.screenBuffer);
    return this;
  }

  public Display clear() {
    this.clearBuffer((byte) 0x00);
    this.writeBuffer(this.screenBuffer);
    return this;
  }

  public void restore() {
    this.writeBuffer(this.originalBuffer);
  }

  private void clearBuffer(final byte value) {
    for (int i = 0; i < this.screenBuffer.length; i++)
      this.screenBuffer[i] = value;
  }

  private void writeBuffer(final byte[] buffer) { Ev3DevFs.write(FRAMEBUFFER_PATH, buffer); }
}
