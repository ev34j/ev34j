package com.ev34j.core.sensor;

public class AllSignaturesValues {

  private final int signature;
  private final int x;
  private final int y;
  private final int width;
  private final int height;
  private final int angle;

  public AllSignaturesValues(final int signature, final int x, final int y, final int width, final int height, final int angle) {
    this.signature = signature;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
    this.angle = angle;
  }

  public int getSignature() { return this.signature; }

  public int getX() { return this.x; }

  public int getY() { return this.y; }

  public int getWidth() { return this.width; }

  public int getHeight() { return this.height; }

  public int getAngle() { return this.angle; }
}
