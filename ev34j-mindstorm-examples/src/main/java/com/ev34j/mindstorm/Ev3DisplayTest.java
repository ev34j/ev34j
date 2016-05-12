package com.ev34j.mindstorm;

import com.ev34j.core.utils.Delay;
import com.ev34j.mindstorm.display.Ev3Display;

public class Ev3DisplayTest {

  public static void main(String[] args) {

    /*
    for (int i = 0; i < 2; i++) {
      Ev3Display.clear();
      Delay.delayMillis(200);
      Ev3Display.inverse();
      Delay.delayMillis(200);
    }

    Ev3Display.clear();

    for (int i = 0; i < 127; i++) {
      Ev3Display.drawHorizontalLine(i);
      Ev3Display.refresh();
      Delay.delayMillis(10);
    }

    Ev3Display.clear();

    for (int i = 0; i < 177; i++) {
      Ev3Display.drawVerticalLine(i);
      Ev3Display.refresh();
      Delay.delayMillis(10);
    }
    */

    Ev3Display.clear();

    Ev3Display.drawRect(0, 0, 10, 10);
    for (int i = 0; i < 100; i++) {
      Ev3Display.refresh();
      Delay.delayMillis(100);
    }

    Ev3Display.restore();
  }
}
