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

  private final BufferedImage image        = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, BufferedImage.TYPE_BYTE_BINARY);
  private final int[]         drawBuffer   = new int[SCREEN_WIDTH * SCREEN_HEIGHT];
  private final byte[]        screenBuffer = new byte[SIZE];

  private final byte[]   originalBuffer;
  private final Graphics graphics;

  private Display() {
    if (!Platform.isEv3Brick())
      throw new DeviceNotSupportedException(this.getClass());

    this.originalBuffer = Ev3DevFs.readBytes(FRAMEBUFFER_PATH);
    this.graphics = this.image.getGraphics();
    this.graphics.setColor(Color.white);
  }

  public void inverse() {
    for (int i = 0; i < this.screenBuffer.length; i++)
      this.screenBuffer[i] = (byte) ~this.screenBuffer[i];
  }

  public Display refresh() {
    this.mapPixelsToScreenBuffer();
    this.writeBuffer(this.screenBuffer);
    return this;
  }

  public void clearGraphicsBuffer() {
    this.graphics.setColor(Color.black);
    this.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    this.graphics.setColor(Color.white);
    this.clearScreenBuffer();
  }

  public void clearDisplay() {
    this.clearScreenBuffer();
    this.refresh();
  }

  public void drawHorizontalLine(final int row) {
    final int base = row * LINE_LENGTH;
    for (int col = 0; col < LINE_LENGTH; col++)
      this.screenBuffer[base + col] = (byte) 0xFF;
  }

  public void drawVerticalLine(final int col) {
    final int offset = col / 8;
    final int pattern = 1 << (col % 8);
    for (int row = 0; row < SCREEN_HEIGHT; row++) {
      final int pos = (row * LINE_LENGTH) + offset;
      this.screenBuffer[pos] = (byte) (this.screenBuffer[pos] | pattern);
    }
  }

  public void drawLine(final int x1, final int y1, final int x2, final int y2) {
    this.graphics.drawLine(x1, y1, x2, y2);
  }

  public void drawString(String str, int x, int y, final int size) {
    this.graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, size));
    this.graphics.drawString(str, x, y);
  }

  public void drawRect(final int x, final int y, final int width, final int height) {
    this.graphics.drawRect(x, y, width, height);
  }

  public void fillRect(final int x, final int y, final int width, final int height) {
    this.graphics.fillRect(x, y, width, height);
  }

  private int[] getPixels() {
    for (int i = 0; i < this.drawBuffer.length; i++)
      this.drawBuffer[i] = 0;
    return this.image.getData().getPixels(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT, this.drawBuffer);
  }


  public void restore() { this.writeBuffer(this.originalBuffer); }

  private void clearScreenBuffer() {
    for (int i = 0; i < this.screenBuffer.length; i++)
      this.screenBuffer[i] = 0;
  }

  private void writeBuffer(final byte[] buffer) { Ev3DevFs.write(FRAMEBUFFER_PATH, buffer); }

  private void printPixels(final int numLines) {
    final int[] pixels = this.getPixels();
    System.out.println("****Pixels****");
    for (int row = 0; row < SCREEN_HEIGHT; row++) {
      final int row_offset = row * SCREEN_WIDTH;
      if (row <= numLines) {
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

  private void printScreenBuffer(final int numLines) {
    System.out.println("****ScreenBuffer****");
    for (int pos = 0; pos < this.screenBuffer.length; pos++) {
      if (pos > 0 && pos % LINE_LENGTH == 0) {
        System.out.println();
        System.out.flush();
      }

      if (pos > LINE_LENGTH * numLines) {
        System.out.println();
        System.out.flush();
        break;
      }

      for (int i = 0; i < 8; i++)
        System.out.print(format("%d", (this.screenBuffer[pos] & (1 << i)) >> i));
      System.out.print(" ");
    }
  }

  private void mapPixelsToScreenBuffer() {
    final int[] pixels = this.getPixels();
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
}
