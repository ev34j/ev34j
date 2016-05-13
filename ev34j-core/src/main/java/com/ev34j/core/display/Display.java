package com.ev34j.core.display;

import com.ev34j.core.common.DeviceNotSupportedException;
import com.ev34j.core.common.Platform;
import com.ev34j.core.utils.Ev3DevFs;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.atomic.AtomicReference;

import static java.awt.image.BufferedImage.TYPE_BYTE_BINARY;
import static java.lang.String.format;

public class Display {
  private final static AtomicReference<Display> SINGLETON = new AtomicReference<>();

  public static Display getInstance() {
    if (SINGLETON.get() == null)
      SINGLETON.compareAndSet(null, new Display());
    return SINGLETON.get();
  }

  private static String FRAMEBUFFER_PATH = "/dev/fb0";
  private static int    HEIGHT_PIXELS    = 128;  // pixels
  private static int    WIDTH_BYTES      = 24;   // bytes
  private static int    WIDTH_PIXELS     = 192;  // pixels -- only 178 actually displayed
  private static int    TOTAL_PIXELS     = 3072; // bytes

  private final BufferedImage image        = new BufferedImage(WIDTH_PIXELS, HEIGHT_PIXELS, TYPE_BYTE_BINARY);
  private final int[]         drawBuffer   = new int[WIDTH_PIXELS * HEIGHT_PIXELS];
  private final byte[]        screenBuffer = new byte[TOTAL_PIXELS];

  private int  currentSize = -1;
  private Font currentFont = null;

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
    this.fillRect(0, 0, WIDTH_PIXELS, HEIGHT_PIXELS);
    this.graphics.setColor(Color.white);
  }

  public void drawHorizontalLine(final int row) {
    final int base = row * WIDTH_BYTES;
    for (int col = 0; col < WIDTH_BYTES; col++)
      this.screenBuffer[base + col] = (byte) 0xFF;
  }

  public void drawVerticalLine(final int col) {
    final int offset = col / 8;
    final int pattern = 1 << (col % 8);
    for (int row = 0; row < HEIGHT_PIXELS; row++) {
      final int pos = (row * WIDTH_BYTES) + offset;
      this.screenBuffer[pos] = (byte) (this.screenBuffer[pos] | pattern);
    }
  }

  public void drawLine(final int x1, final int y1, final int x2, final int y2) {
    this.graphics.drawLine(x1, y1, x2, y2);
  }

  public void drawString(String str, int x, int y, final int size) {
    if (this.currentSize != size) {
      this.currentSize = size;
      this.currentFont = new Font(Font.SANS_SERIF, Font.PLAIN, this.currentSize);
    }
    this.graphics.setFont(this.currentFont);
    this.graphics.drawString(str, x, y);
  }

  public void drawPoint(final int x, final int y) {
    this.graphics.drawRect(x, y, 1, 1);
  }

  public void drawRect(final int x, final int y, final int width, final int height) {
    this.graphics.drawRect(x, y, width, height);
  }

  public void drawOval(final int x, final int y, final int width, final int height) {
    this.graphics.drawOval(x, y, width, height);
  }

  public void drawArc(final int x,
                      final int y,
                      final int width,
                      final int height,
                      final int startAngle,
                      final int arcAngle) {
    this.graphics.drawArc(x, y, width, height, startAngle, arcAngle);
  }

  public void fillRect(final int x, final int y, final int width, final int height) {
    this.graphics.fillRect(x, y, width, height);
  }

  public void fillOval(final int x, final int y, final int width, final int height) {
    this.graphics.fillOval(x, y, width, height);
  }

  public void restore() { this.writeBuffer(this.originalBuffer); }

  public void clearScreenBuffer() {
    for (int i = 0; i < this.screenBuffer.length; i++)
      this.screenBuffer[i] = 0;
  }

  private void writeBuffer(final byte[] buffer) { Ev3DevFs.write(FRAMEBUFFER_PATH, buffer); }

  private int[] getPixels() {
    for (int i = 0; i < this.drawBuffer.length; i++)
      this.drawBuffer[i] = 0;
    return this.image.getData().getPixels(0, 0, WIDTH_PIXELS, HEIGHT_PIXELS, this.drawBuffer);
  }

  private void mapPixelsToScreenBuffer() {
    final int[] pixels = this.getPixels();
    int pos = 0;
    for (int row = 0; row < HEIGHT_PIXELS; row++) {
      final int rowOffset = row * WIDTH_PIXELS;
      for (int col = 0; col < WIDTH_PIXELS; col += 8) {
        final int offset = rowOffset + col;
        byte pattern = 0;
        for (int i = 0; i < 8; i++)
          pattern = (byte) (pattern | pixels[offset + i] << i);
        if (pattern != 0)
          this.screenBuffer[pos] = (byte) (this.screenBuffer[pos] | pattern);
        pos++;
      }
    }
  }

  private void printPixels(final int numLines) {
    final int[] pixels = this.getPixels();
    System.out.println("****Pixels****");
    for (int row = 0; row < HEIGHT_PIXELS; row++) {
      final int row_offset = row * WIDTH_PIXELS;
      if (row <= numLines) {
        for (int col = 0; col < WIDTH_PIXELS; col++) {
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
      if (pos > 0 && pos % WIDTH_BYTES == 0) {
        System.out.println();
        System.out.flush();
      }

      if (pos > WIDTH_BYTES * numLines) {
        System.out.println();
        System.out.flush();
        break;
      }

      for (int i = 0; i < 8; i++)
        System.out.print(format("%d", (this.screenBuffer[pos] & (1 << i)) >> i));
      System.out.print(" ");
    }
  }
}
