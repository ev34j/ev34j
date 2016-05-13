package com.ev34j.mindstorm.display;

import com.ev34j.core.display.Display;

public class Ev3Display {

  private Ev3Display() { }

  public static void clear() {
    resetDisplay();
    refresh();
  }

  public static void resetDisplay() {
    Display.getInstance().clearGraphicsBuffer();
    Display.getInstance().clearScreenBuffer();
    refresh();
  }

  public static void inverse() {
    Display.getInstance().inverse();
    refresh();
  }

  public static void refresh() { Display.getInstance().refresh(); }

  public static void drawString(final String str, final int x, final int y, final int size, final boolean color) {
    Display.getInstance().setBlack(color);
    Display.getInstance().drawString(str, x, y, size);
    refresh();
  }

  public static void drawPoint(final int x, final int y, final boolean color) {
    Display.getInstance().setBlack(color);
    Display.getInstance().drawPoint(x, y);
    refresh();
  }

  public static void drawLine(final int x1, final int y1, final int x2, final int y2, final boolean color) {
    Display.getInstance().setBlack(color);
    Display.getInstance().drawLine(x1, y1, x2, y2);
    refresh();
  }

  public static void drawRect(final int x, final int y, final int width, final int height, final boolean color) {
    Display.getInstance().setBlack(color);
    Display.getInstance().drawRect(x, y, width, height);
    refresh();
  }

  public static void drawOval(final int x, final int y, final int width, final int height, final boolean color) {
    Display.getInstance().setBlack(color);
    Display.getInstance().drawOval(x, y, width, height);
    refresh();
  }

  public static void drawArc(final int x,
                             final int y,
                             final int width,
                             final int height,
                             final int startAngle,
                             final int arcAngle,
                             final boolean color) {
    Display.getInstance().setBlack(color);
    Display.getInstance().drawArc(x, y, width, height, startAngle, arcAngle);
    refresh();
  }

  public static void fillRect(final int x, final int y, final int width, final int height, final boolean color) {
    Display.getInstance().setBlack(color);
    Display.getInstance().fillRect(x, y, width, height);
    refresh();
  }

  public static void fillOval(final int x, final int y, final int width, final int height, final boolean color) {
    Display.getInstance().setBlack(color);
    Display.getInstance().fillOval(x, y, width, height);
    refresh();
  }

  public static void drawVerticalLine(final int col, final boolean color) {
    Display.getInstance().setBlack(color);
    Display.getInstance().drawVerticalLine(col);
    refresh();
  }

  public static void drawHorizontalLine(final int row, final boolean color) {
    Display.getInstance().setBlack(color);
    Display.getInstance().drawHorizontalLine(row);
    refresh();
  }

  public static void restoreOriginalScreen() { Display.getInstance().restore(); }
}
