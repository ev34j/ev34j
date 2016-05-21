package com.ev34j.core.sensor;

public class SignatureValues {

  private final int count;
  private final int x;
  private final int y;
  private final int width;
  private final int height;

  public SignatureValues(final int count, final int x, final int y, final int width, final int height) {
    this.count = count;
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public int getCount() { return this.count; }

  public int getX() { return this.x; }

  public int getY() { return this.y; }

  public int getWidth() { return this.width; }

  public int getHeight() { return this.height; }
}
