package com.ev34j.mindstorm;

import com.ev34j.mindstorm.sensor.PixyCamera;
import com.ev34j.mindstorm.sensor.SignatureValues;
import com.ev34j.mindstorm.utils.Wait;

import static java.lang.String.format;

public class PixyTest {

  public static void main(String[] args) {

    final PixyCamera ps = new PixyCamera(3);

    for (int i = 0; i < 60; i++) {
      final SignatureValues vals = ps.getSignature3Values();
      System.out.println(format("count: %d, x: %d y: %d width: %d height: %d",
                                vals.getCount(), vals.getX(), vals.getY(), vals.getWidth(), vals.getHeight()));
      Wait.forMillis(500);
    }

  }
}
