package com.ev34j.mindstorm.display;

import com.ev34j.core.display.Display;

public class Ev3Display {

  private Ev3Display() { }

  public static void clear() { Display.getInstance().clear(); }

  public static void inverse() { Display.getInstance().inverse(); }
}
