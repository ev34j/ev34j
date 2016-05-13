package com.ev34j.mindstorm;

import com.ev34j.core.utils.Delay;
import com.ev34j.mindstorm.display.Ev3Display;
import com.ev34j.mindstorm.sound.Ev3Sound;

import java.awt.Polygon;

public class Ev3DisplayTest {

  public static void main(String[] args) {

    Ev3Display.clear();

    /*
    Ev3Sound.sayAsEnglish("Flashing screen");
    for (int i = 0; i < 10; i++) {
      Ev3Display.clear();
      Delay.millis(200);
      Ev3Display.inverse();
      Delay.millis(200);
    }

    Ev3Display.clear();

    Ev3Sound.sayAsEnglish("Curtain down");
    for (int i = 0; i < Ev3Display.getHeight(); i++) {
      Ev3Display.drawHorizontalLine(false, i, true);
      Delay.millis(10);
    }

    Ev3Sound.sayAsEnglish("Curtain up");
    for (int i = Ev3Display.getHeight() - 1; i >= 0; i--) {
      Ev3Display.drawHorizontalLine(false, i, false);
      Delay.millis(10);
    }

    Ev3Sound.sayAsEnglish("Curtain right");
    for (int i = 0; i < Ev3Display.getWidth(); i++) {
      Ev3Display.drawVerticalLine(false, i, true);
      Delay.millis(10);
    }

    Ev3Sound.sayAsEnglish("Curtain left");
    for (int i = Ev3Display.getWidth() - 1; i >= 0; i--) {
      Ev3Display.drawVerticalLine(false, i, false);
      Delay.millis(10);
    }

    Ev3Sound.sayAsEnglish("Square");
    Ev3Display.drawLine(true, 10, 10, 100, 10, true);
    Ev3Display.drawLine(false, 100, 10, 100, 100, true);
    Ev3Display.drawLine(false, 100, 100, 10, 100, true);
    Ev3Display.drawLine(false, 10, 100, 10, 10, true);
    for (int i = 0; i < 30; i++) {
      Ev3Display.refresh();
      Delay.millis(100);
    }

    Ev3Sound.sayAsEnglish("Floating rectangle");
    for (int i = 0; i < 60; i += 3) {
      Ev3Display.drawRect(true, i, i, 20, 20, false, true);
      Delay.millis(100);
    }

    Ev3Sound.sayAsEnglish("Floating solid rectangle");
    for (int i = 0; i < 60; i += 3) {
      Ev3Display.drawRect(true, i, i, 20, 20, true, true);
      Delay.millis(100);
    }

    Ev3Sound.sayAsEnglish("Floating oval");
    for (int i = 0; i < 60; i += 3) {
      Ev3Display.drawOval(true, i, i, 20, 20, false, true);
      Delay.millis(100);
    }

    Ev3Sound.sayAsEnglish("Floating solid oval");
    for (int i = 0; i < 60; i += 3) {
      Ev3Display.drawOval(true, i, i, 20, 20, true, true);
      Delay.millis(100);
    }

    Ev3Sound.sayAsEnglish("Floating arc");
    for (int i = 0; i < 60; i += 3) {
      Ev3Display.drawArc(true, i, i, 40, 40, 40, 180, false, true);
      Delay.millis(100);
    }
    Ev3Sound.sayAsEnglish("Floating solid arc");
    for (int i = 0; i < 60; i += 3) {
      Ev3Display.drawArc(true, i, i, 40, 40, 40, 180, true, true);
      Delay.millis(100);
    }
    */


    Ev3Sound.sayAsEnglish("Floating polygon");
    for (int i = 0; i < 60; i += 3) {
      final Polygon polygon = new Polygon(new int[] {i, i + 20, i + 20, i}, new int[] {i, i, i + 20, i + 20}, 4);
      Ev3Display.drawPolygon(true, polygon, false, true);
      Delay.millis(100);
    }
    Ev3Sound.sayAsEnglish("Floating solid polygon");
    for (int i = 0; i < 60; i += 3) {
      final Polygon polygon = new Polygon(new int[] {i, i + 20, i + 20, i}, new int[] {i, i, i + 20, i + 20}, 4);
      Ev3Display.drawPolygon(true, polygon, true, true);
      Delay.millis(100);
    }

    /*

    Ev3Sound.sayAsEnglish("Floating text");
    for (int i = 20; i < 60; i += 3) {
      Ev3Display.drawString(true, "Hello world", i, i, i / 2, true);
      Delay.millis(100);
    }
    */


    Ev3Display.restoreOriginalScreen();
  }
}
