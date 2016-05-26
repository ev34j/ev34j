package com.ev34j.mindstorm;

import com.ev34j.mindstorm.display.Ev3Display;
import com.ev34j.mindstorm.sound.Ev3Sound;
import com.ev34j.mindstorm.time.Wait;

import java.awt.Polygon;

public class Ev3DisplayTest {

  public static void main(String[] args) {

    Ev3Display.clear();

    Ev3Sound.say("Flashing screen", 100);
    for (int i = 0; i < 10; i++) {
      Ev3Display.clear();
      Wait.forMillis(200);
      Ev3Display.reverse();
      Wait.forMillis(200);
    }

    Ev3Display.clear();

    Ev3Sound.say("Curtain down", 100);
    for (int i = 0; i < Ev3Display.getHeight(); i++) {
      Ev3Display.drawHorizontalLine(false, i, true);
      Wait.forMillis(10);
    }

    Ev3Sound.say("Curtain up", 100);
    for (int i = Ev3Display.getHeight() - 1; i >= 0; i--) {
      Ev3Display.drawHorizontalLine(false, i, false);
      Wait.forMillis(10);
    }

    Ev3Sound.say("Curtain right", 100);
    for (int i = 0; i < Ev3Display.getWidth(); i++) {
      Ev3Display.drawVerticalLine(false, i, true);
      Wait.forMillis(10);
    }

    Ev3Sound.say("Curtain left", 100);
    for (int i = Ev3Display.getWidth() - 1; i >= 0; i--) {
      Ev3Display.drawVerticalLine(false, i, false);
      Wait.forMillis(10);
    }

    Ev3Sound.say("Square", 100);
    Ev3Display.drawLine(true, 10, 10, 100, 10, true);
    Ev3Display.drawLine(false, 100, 10, 100, 100, true);
    Ev3Display.drawLine(false, 100, 100, 10, 100, true);
    Ev3Display.drawLine(false, 10, 100, 10, 10, true);
    for (int i = 0; i < 30; i++) {
      Ev3Display.refresh();
      Wait.forMillis(100);
    }

    Ev3Sound.say("Floating rectangle", 100);
    for (int i = 0; i < 60; i += 3) {
      Ev3Display.drawRect(true, i, i, 20, 20, false, true);
      Wait.forMillis(100);
    }

    Ev3Sound.say("Floating solid rectangle", 100);
    for (int i = 0; i < 60; i += 3) {
      Ev3Display.drawRect(true, i, i, 20, 20, true, true);
      Wait.forMillis(100);
    }

    Ev3Sound.say("Floating oval", 100);
    for (int i = 0; i < 60; i += 3) {
      Ev3Display.drawOval(true, i, i, 20, 20, false, true);
      Wait.forMillis(100);
    }

    Ev3Sound.say("Floating solid oval", 100);
    for (int i = 0; i < 60; i += 3) {
      Ev3Display.drawOval(true, i, i, 20, 20, true, true);
      Wait.forMillis(100);
    }

    Ev3Sound.say("Floating arc", 100);
    for (int i = 0; i < 60; i += 3) {
      Ev3Display.drawArc(true, i, i, 40, 40, 40, 180, false, true);
      Wait.forMillis(100);
    }
    Ev3Sound.say("Floating solid arc", 100);
    for (int i = 0; i < 60; i += 3) {
      Ev3Display.drawArc(true, i, i, 40, 40, 40, 180, true, true);
      Wait.forMillis(100);
    }

    Ev3Sound.say("Floating polygon", 100);
    for (int i = 0; i < 60; i += 3) {
      final Polygon polygon = new Polygon(new int[] {i, i + 20, i + 20, i}, new int[] {i, i, i + 20, i + 20}, 4);
      Ev3Display.drawPolygon(true, polygon, false, true);
      Wait.forMillis(100);
    }
    Ev3Sound.say("Floating solid polygon", 100);
    for (int i = 0; i < 60; i += 3) {
      final Polygon polygon = new Polygon(new int[] {i, i + 20, i + 20, i}, new int[] {i, i, i + 20, i + 20}, 4);
      Ev3Display.drawPolygon(true, polygon, true, true);
      Wait.forMillis(100);
    }


    Ev3Sound.say("Floating text", 100);
    for (int i = 20; i < 60; i += 3) {
      Ev3Display.drawString(true, "Hello world", i, i, i / 2, true);
      Wait.forMillis(100);
    }

    Ev3Display.restoreOriginalScreen();
  }
}
