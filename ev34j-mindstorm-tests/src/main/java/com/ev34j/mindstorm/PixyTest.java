package com.ev34j.mindstorm;

import com.ev34j.core.sensor.ColorCodeValues;
import com.ev34j.mindstorm.sensor.Ev3PixySensor;
import com.ev34j.mindstorm.time.Wait;

import static java.lang.String.format;

public class PixyTest {

  public static void main(String[] args) {

    final Ev3PixySensor ps = new Ev3PixySensor(3);
/*
    // Sample signature 1
    for (int i = 0; i < 60; i++) {
      final SignatureValues sv = ps.getSignature(1);
      System.out.println(format("Signature# 1 Count: %d, (%d,%d) %d x %d area: %d",
                                sv.getCount(), sv.getX(),
                                sv.getY(), sv.getWidth(), sv.getHeight(), sv.getWidth() * sv.getHeight()));
      Wait.forMillis(250);
    }

    // Sample signature 2
    for (int i = 0; i < 60; i++) {
      final SignatureValues sv = ps.getSignature(2);
      System.out.println(format("Signature# 2 Count: %d, (%d,%d) %d x %d area: %d",
                                sv.getCount(), sv.getX(),
                                sv.getY(), sv.getWidth(), sv.getHeight(), sv.getWidth() * sv.getHeight()));
      Wait.forMillis(250);
    }

    for (int i = 0; i < 60; i++) {
      final AllSignaturesValues as = ps.getAllSignatures();
      System.out.println(format("Signature#: %d (%d,%d) %d x %d area: %d angle: %d",
                                as.getSignature(), as.getX(), as.getY(),
                                as.getWidth(), as.getHeight(), as.getWidth() * as.getHeight(), as.getAngle()));
      Wait.forMillis(250);
    }

*/
    for (int i = 0; i < 60; i++) {
      final ColorCodeValues cc = ps.getColorCode();
      System.out.println(format("Color code (%d,%d) %d x %d area: %d angle: %d",
                                cc.getX(), cc.getY(),
                                cc.getWidth(), cc.getHeight(), cc.getWidth() * cc.getHeight(), cc.getAngle()));
      Wait.forMillis(250);
    }
/*
    for (int i = 0; i < 60; i++) {
      System.out.println(format("Angle %d", ps.getAngle()));
      Wait.forMillis(250);
    }
    */
  }
}
