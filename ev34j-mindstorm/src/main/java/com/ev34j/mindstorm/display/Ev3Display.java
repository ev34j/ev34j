package com.ev34j.mindstorm.display;

import com.ev34j.core.display.Display;

public class Ev3Display {

  private Ev3Display() { }

  public static void clearDisplay() { Display.getInstance().clearDisplay(); }

  public static void clearBuffer() { Display.getInstance().clearGraphicsBuffer(); }

  public static void inverse() { Display.getInstance().inverse(); }

  public static void refresh() { Display.getInstance().refresh(); }

  public static void drawString(final String str, final int x, final int y, final int size) {
    Display.getInstance().drawString(str, x, y, size);
  }

  public static void drawLine(final int x1, final int y1, final int x2, final int y2) {
    Display.getInstance().drawLine(x1, y1, x2, y2);
  }

  public static void drawRect(final int x, final int y, final int width, final int height) {
    Display.getInstance().drawRect(x, y, width, height);
  }

  public static void fillRect(final int x, final int y, final int width, final int height) {
    Display.getInstance().fillRect(x, y, width, height);
  }

  public static void drawVerticalLine(final int x) { Display.getInstance().drawVerticalLine(x); }

  public static void drawHorizontalLine(final int y) { Display.getInstance().drawHorizontalLine(y); }

  public static void restore() { Display.getInstance().restore(); }
}
