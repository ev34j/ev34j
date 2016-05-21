package com.ev34j.mindstorm;

import com.ev34j.core.sensor.AllSignaturesValues;
import com.ev34j.core.sensor.ColorCodeValues;
import com.ev34j.core.sensor.SignatureValues;
import com.ev34j.mindstorm.sensor.Ev3PixySensor;
import com.ev34j.mindstorm.utils.Wait;

import static java.lang.String.format;

public class PixyTest {

  public static void main(String[] args) {

    final Ev3PixySensor ps = new Ev3PixySensor(3);

    // Sample signature 3
    for (int i = 0; i < 60; i++) {
      final SignatureValues sv = ps.getSignatureValues(3);
      System.out.println(format("Count: %d, (%d,%d) %d x %d area: %d",
                                sv.getCount(), sv.getX(),
                                sv.getY(), sv.getWidth(), sv.getHeight(), sv.getWidth() * sv.getHeight()));
      Wait.forMillis(250);
    }

    for (int i = 0; i < 60; i++) {
      final AllSignaturesValues as = ps.getAllSignaturesValues();
      System.out.println(format("Signature#: %d (%d,%d) %d x %d area: %d angle: %d",
                                as.getSignature(), as.getX(), as.getY(),
                                as.getWidth(), as.getHeight(), as.getWidth() * as.getHeight(), as.getAngle()));
      Wait.forMillis(250);
    }

    for (int i = 0; i < 60; i++) {
      final ColorCodeValues cc = ps.getColorCodeValues();
      System.out.println(format("Color code (%d,%d) %d x %d area: %d angle: %d",
                                cc.getX(), cc.getY(),
                                cc.getWidth(), cc.getHeight(), cc.getWidth() * cc.getHeight(), cc.getAngle()));
      Wait.forMillis(250);
    }

    for (int i = 0; i < 60; i++) {
      System.out.println(format("Angle %d", ps.getAngle()));
      Wait.forMillis(250);
    }
  }
}
