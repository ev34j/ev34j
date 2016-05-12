package com.ev34j.core.display;

import com.ev34j.core.common.DeviceNotSupportedException;
import com.ev34j.core.common.Platform;
import com.ev34j.core.utils.Ev3DevFs;

import java.awt.*;
import java.awt.image.BufferedImage;
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

  // byte width =

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
    return this.refresh();
  }

  public Display refresh() {
    this.writeBuffer(this.screenBuffer);
    return this;
  }

  public Display clear() {
    this.clearBuffer((byte) 0x00);
    return this.refresh();
  }

  public Display drawHorizontalLine(final int row) {
    final int base = row * LINE_LENGTH;
    for (int col = 0; col < LINE_LENGTH; col++)
      this.screenBuffer[base + col] = (byte) 0xFF;
    return this;
  }

  public Display drawVerticalLine(final int col) {
    final int offset = col / 8;
    final int pattern = 1 << (col % 8);
    for (int row = 0; row < SCREEN_HEIGHT; row++) {
      final int pos = (row * LINE_LENGTH) + offset;
      this.screenBuffer[pos] = (byte) (this.screenBuffer[pos] | pattern);
    }
    return this;
  }

  public Display drawRect(final int x, final int y, final int width, final int height) {
    final BufferedImage image = new BufferedImage(LINE_LENGTH * 8, SCREEN_HEIGHT, BufferedImage.TYPE_BYTE_BINARY);
    final Graphics graphics = image.getGraphics();
    graphics.drawRect(x, y, width, height);

    final int[] buffer = new int[LINE_LENGTH * 8 * SCREEN_HEIGHT];
    final int[] pixels = image.getData().getPixels(0, 0, LINE_LENGTH * 8, SCREEN_HEIGHT, buffer);

    int pos = 0;
    for (int row = 0; row < SCREEN_HEIGHT; row++) {
      /*
      for (int col = 0; col < LINE_LENGTH * 8; col++) {
        System.out.print(format("%d", pixels[(row * col) + col]));
        if ((col + 1) % 8 == 0)
          System.out.print(" ");

      }
      System.out.println();
      */
      for (int col = 0; col < LINE_LENGTH * 8; col += 8) {
        final int offset = (row * col) + col;
        final int val0 = pixels[offset];
        final int val1 = pixels[offset + 1];
        final int val2 = pixels[offset + 2];
        final int val3 = pixels[offset + 3];
        final int val4 = pixels[offset + 4];
        final int val5 = pixels[offset + 5];
        final int val6 = pixels[offset + 6];
        final int val7 = pixels[offset + 7];
        final byte combo = (byte) (val0 << 7 | val1 << 6 | val2 << 5 | val3 << 4 | val4 << 3 | val5 << 2 | val6 << 1 | val7);
        this.screenBuffer[pos] = (byte) (this.screenBuffer[pos] | combo);
        pos++;
      }
    }

    return this.refresh();
  }

  public void restore() { this.writeBuffer(this.originalBuffer); }

  private void clearBuffer(final byte value) {
    for (int i = 0; i < this.screenBuffer.length; i++)
      this.screenBuffer[i] = value;
  }

  private void writeBuffer(final byte[] buffer) { Ev3DevFs.write(FRAMEBUFFER_PATH, buffer); }
}
