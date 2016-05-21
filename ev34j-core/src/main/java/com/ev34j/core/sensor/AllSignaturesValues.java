package com.ev34j.core.sensor;

public class AllSignaturesValues {

  private final int sigLowByte;
  private final int sigHighByte;
  private final int x;
  private final int y;
  private final int width;
  private final int height;
  private final int angle;

  public AllSignaturesValues(final int sigLowByte,
                             final int sigHighByte,
                             final int x,
                             final int y,
                             final int width,
                             final int height,
                             final int angle) {
    this.sigLowByte = sigLowByte;
    this.sigHighByte = sigHighByte;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.angle = angle;
  }

  public int getSigLowByte() { return this.sigLowByte; }

  public int getSigHighByte() { return this.sigHighByte; }

  public int getSignature() {
    final byte sigLowByte = (byte) this.getSigLowByte();
    final byte sigHighByte = (byte) this.getSigHighByte();
    return sigHighByte << 8 | sigLowByte;
  }

  public int getX() { return this.x; }

  public int getY() { return this.y; }

  public int getWidth() { return this.width; }

  public int getHeight() { return this.height; }

  public int getAngle() { return this.angle; }
}
