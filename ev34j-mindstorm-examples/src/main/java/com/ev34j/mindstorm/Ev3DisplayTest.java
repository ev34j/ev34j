package com.ev34j.mindstorm;

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

    Ev3Display.clearDisplay();

    for (int i = 0; i < 60; i += 3) {
      Ev3Display.resetDisplay();
      Ev3Display.drawRect(i, i, 10, 10);
      Ev3Display.refreshDisplay();
    }

    Ev3Display.clearDisplay();

    for (int i = 0; i < 60; i += 3) {
      Ev3Display.resetDisplay();
      Ev3Display.fillRect(i, i, 10, 10);
      Ev3Display.refreshDisplay();
    }

    for (int i = 0; i < 60; i += 3) {
      Ev3Display.resetDisplay();
      Ev3Display.drawString("Hello world", i, i, i / 2);
      Ev3Display.refreshDisplay();
    }

    /*
    //Ev3Display.drawLine(0, 0, 6, 0);
    //Ev3Display.drawLine(0, 0, 0, 6);
    for (int i = 0; i < 200; i++) {
      Ev3Display.refresh();
      Delay.delayMillis(100);
    }
*/

    Ev3Display.restoreOriginalScreen();
  }
}
