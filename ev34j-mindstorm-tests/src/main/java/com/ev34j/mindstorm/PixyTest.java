package com.ev34j.mindstorm;

import com.ev34j.mindstorm.sensor.PixyCamera;
import com.ev34j.mindstorm.utils.Wait;

import static java.lang.String.format;

public class PixyTest {

  public static void main(String[] args) {

    final PixyCamera ps = new PixyCamera(3);

    for (int i = 0; i < 60; i++) {
      System.out.println(format("count: %d, x: %d y: %d width: %d height: %d",
                                ps.getCount(3), ps.getX(3), ps.getY(3), ps.getWidth(3), ps.getHeight(3)));
      Wait.forMillis(500);
    }

  }
}
