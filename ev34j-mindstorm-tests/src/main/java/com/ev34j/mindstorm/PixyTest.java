package com.ev34j.mindstorm;

import com.ev34j.core.sensor.SignatureValues;
import com.ev34j.mindstorm.sensor.Ev3PixySensor;
import com.ev34j.mindstorm.utils.Wait;

import static java.lang.String.format;

public class PixyTest {

  public static void main(String[] args) {

    final Ev3PixySensor ps = new Ev3PixySensor(3);

    for (int i = 0; i < 60; i++) {
      final SignatureValues vals = ps.getSignatureValues(3);
      System.out.println(format("count: %d, x: %d y: %d width: %d height: %d",
                                vals.getCount(), vals.getX(), vals.getY(), vals.getWidth(), vals.getHeight()));
      Wait.forMillis(500);
    }

  }
}
