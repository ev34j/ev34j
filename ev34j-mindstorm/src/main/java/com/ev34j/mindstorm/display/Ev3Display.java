package com.ev34j.mindstorm.display;

import com.ev34j.core.display.ev3.Display;

import java.awt.Polygon;

public class Ev3Display {

  private Ev3Display() { }

  public static int getWidth() { return Display.getInstance().getDisplayWidth(); }

  public static int getHeight() { return Display.getInstance().getDisplayHeight(); }

  public static void clear() {
    final Display display = Display.getInstance();
    display.clearGraphicsBuffer();
    display.clearScreenBuffer();
    refresh();
  }

  public static void reverse() {
    Display.getInstance().reverse();
    refresh();
  }

  public static void refresh() { Display.getInstance().refresh(); }

  public static void drawPoint(final boolean clearScreen, final int x, final int y, final boolean color) {
    final Display display = Display.getInstance();
    if (clearScreen)
      clear();
    display.setColor(color);
    display.drawRect(x, y, 1, 1);
    display.mapGraphicsToScreenBuffer();
    refresh();
  }

  public static void drawLine(final boolean clearScreen,
                              final int x1,
                              final int y1,
                              final int x2,
                              final int y2,
                              final boolean color) {
    final Display display = Display.getInstance();
    if (clearScreen)
      clear();
    display.setColor(color);
    display.drawLine(x1, y1, x2, y2);
    display.mapGraphicsToScreenBuffer();
    refresh();
  }

  public static void drawString(final boolean clearScreen,
                                final String str,
                                final int x,
                                final int y,
                                final int size,
                                final boolean color) {
    final Display display = Display.getInstance();
    if (clearScreen)
      clear();
    display.setColor(color);
    display.drawString(str, x, y, size);
    display.mapGraphicsToScreenBuffer();
    refresh();
  }

  public static void drawRect(final boolean clearScreen,
                              final int x,
                              final int y,
                              final int width,
                              final int height,
                              final boolean fill,
                              final boolean color) {
    final Display display = Display.getInstance();
    if (clearScreen)
      clear();
    display.setColor(color);
    if (fill)
      display.fillRect(x, y, width, height);
    else
      display.drawRect(x, y, width, height);
    display.mapGraphicsToScreenBuffer();
    refresh();
  }

  public static void drawOval(final boolean clearScreen,
                              final int x,
                              final int y,
                              final int width,
                              final int height,
                              final boolean fill,
                              final boolean color) {
    final Display display = Display.getInstance();
    if (clearScreen)
      clear();
    display.setColor(color);
    if (fill)
      display.fillOval(x, y, width, height);
    else
      display.drawOval(x, y, width, height);
    display.mapGraphicsToScreenBuffer();
    refresh();
  }

  public static void drawArc(final boolean clearScreen,
                             final int x,
                             final int y,
                             final int width,
                             final int height,
                             final int startAngle,
                             final int arcAngle,
                             final boolean fill,
                             final boolean color) {
    final Display display = Display.getInstance();
    if (clearScreen)
      clear();
    display.setColor(color);
    if (fill)
      display.fillArc(x, y, width, height, startAngle, arcAngle);
    else
      display.drawArc(x, y, width, height, startAngle, arcAngle);
    display.mapGraphicsToScreenBuffer();
    refresh();
  }

  public static void drawPolygon(final boolean clearScreen,
                                 final Polygon polygon,
                                 final boolean fill,
                                 final boolean color) {
    final Display display = Display.getInstance();
    if (clearScreen)
      clear();
    display.setColor(color);
    if (fill)
      display.fillPolygon(polygon);
    else
      display.drawPolygon(polygon);
    display.mapGraphicsToScreenBuffer();
    refresh();
  }

  public static void drawVerticalLine(final boolean clearScreen, final int col, final boolean color) {
    final Display display = Display.getInstance();
    if (clearScreen)
      display.clearScreenBuffer();
    display.setColor(color);
    display.drawVerticalLine(col);
    refresh();
  }

  public static void drawHorizontalLine(final boolean clearScreen, final int row, final boolean color) {
    final Display display = Display.getInstance();
    if (clearScreen)
      display.clearScreenBuffer();
    display.setColor(color);
    display.drawHorizontalLine(row);
    refresh();
  }

  public static void restoreOriginalScreen() { Display.getInstance().restore(); }
}
