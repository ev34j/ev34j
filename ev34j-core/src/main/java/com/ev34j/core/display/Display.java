package com.ev34j.core.display;

import com.ev34j.core.common.DeviceNotSupportedException;
import com.ev34j.core.common.Platform;
import com.ev34j.core.utils.Ev3DevFs;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.String.format;

public class Display {
  private final static AtomicReference<Display> SINGLETON = new AtomicReference<>();

  public static Display getInstance() {
    if (SINGLETON.get() == null)
      SINGLETON.compareAndSet(null, new Display());
    return SINGLETON.get();
  }

  private static String FRAMEBUFFER_PATH = "/dev/fb0";
  private static int    SCREEN_HEIGHT    = 128;  // pixels
  private static int    LINE_LENGTH      = 24;   // bytes
  private static int    SCREEN_WIDTH     = 192;  // pixels 178 actually
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

  public Display drawLine(final int x1, final int y1, final int x2, final int y2) {
    final long start = System.currentTimeMillis();
    final BufferedImage image = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_BYTE_BINARY);
    System.out.println("Finish new BufferedImage " + (System.currentTimeMillis() - start));
    final Graphics graphics = image.getGraphics();
    System.out.println("Finish get Graphics " + (System.currentTimeMillis() - start));
    graphics.drawLine(x1, y1, x2, y2);
    System.out.println("Finish get DrawLine " + (System.currentTimeMillis() - start));

    final int[] buffer = new int[SCREEN_WIDTH * SCREEN_HEIGHT];
    final int[] pixels = image.getData().getPixels(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, buffer);
    System.out.println("Finish getPixels " + (System.currentTimeMillis() - start));
    this.mapPixelsToScreenBuffer(pixels);
    System.out.println("Finish mapping " + (System.currentTimeMillis() - start));

    //this.printPixels(pixels, 10);
    //this.printScreenBuffer(10);
    return this.refresh();
  }

  public Display drawRect(final int x, final int y, final int width, final int height) {
    final BufferedImage image = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_BYTE_BINARY);
    final Graphics graphics = image.getGraphics();
    graphics.drawRect(x, y, width, height);

    final int[] buffer = new int[SCREEN_WIDTH * SCREEN_HEIGHT];
    final int[] pixels = image.getData().getPixels(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, buffer);
    this.mapPixelsToScreenBuffer(pixels);
    return this.refresh();
  }

  private void printPixels(final int[] pixels, final int numlines) {
    System.out.println("****");
    for (int row = 0; row < SCREEN_HEIGHT; row++) {
      final int row_offset = row * SCREEN_WIDTH;
      if (row <= numlines) {
        for (int col = 0; col < SCREEN_WIDTH; col++) {
          if (col > 0 && col % 8 == 0)
            System.out.print(" ");
          System.out.print(format("%d", pixels[row_offset + col]));
        }
        System.out.println();
        System.out.flush();
      }
    }
  }

  private void printScreenBuffer(final int numlines) {
    System.out.println("****");
    for (int pos = 0; pos < this.screenBuffer.length; pos++) {
      if (pos > 0 && pos % LINE_LENGTH == 0) {
        System.out.println();
        System.out.flush();
      }

      if (pos > LINE_LENGTH * numlines) {
        System.out.println();
        System.out.flush();
        break;
      }

      for (int i = 0; i < 8; i++)
        System.out.print(format("%d", (this.screenBuffer[pos] & (1 << i)) >> i));
      System.out.print(" ");
    }
  }

  private void mapPixelsToScreenBuffer(final int[] pixels) {
    int pos = 0;
    for (int row = 0; row < SCREEN_HEIGHT; row++) {
      final int row_offset = row * SCREEN_WIDTH;
      for (int col = 0; col < SCREEN_WIDTH; col += 8) {
        final int offset = row_offset + col;
        final byte val0 = (byte) pixels[offset];
        final byte val1 = (byte) pixels[offset + 1];
        final byte val2 = (byte) pixels[offset + 2];
        final byte val3 = (byte) pixels[offset + 3];
        final byte val4 = (byte) pixels[offset + 4];
        final byte val5 = (byte) pixels[offset + 5];
        final byte val6 = (byte) pixels[offset + 6];
        final byte val7 = (byte) pixels[offset + 7];
        //final byte pattern = (byte) (val0 << 7 | val1 << 6 | val2 << 5 | val3 << 4 | val4 << 3 | val5 << 2 | val6 << 1 | val7);
        final byte pattern = (byte) (val7 << 7 | val6 << 6 | val5 << 5 | val4 << 4 | val3 << 3 | val2 << 2 | val1 << 1 | val0);
        if (pattern != 0x00)
          this.screenBuffer[pos] = (byte) (this.screenBuffer[pos] | pattern);
        pos++;
      }
    }
  }

  public void restore() { this.writeBuffer(this.originalBuffer); }

  private void clearBuffer(final byte value) {
    for (int i = 0; i < this.screenBuffer.length; i++)
      this.screenBuffer[i] = value;
  }

  private void writeBuffer(final byte[] buffer) { Ev3DevFs.write(FRAMEBUFFER_PATH, buffer); }
}
