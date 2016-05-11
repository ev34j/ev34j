package com.ev34j.mindstorm;

import com.ev34j.core.utils.Delay;
import com.ev34j.mindstorm.display.Ev3Display;

public class Ev3DisplayTest {

  public static void main(String[] args) {

    for (int i = 0; i < 20; i++) {
      Ev3Display.clear();
      Delay.delayMillis(200);
      Ev3Display.inverse();
      Delay.delayMillis(200);
    }

    Ev3Display.restore();
  }
}
