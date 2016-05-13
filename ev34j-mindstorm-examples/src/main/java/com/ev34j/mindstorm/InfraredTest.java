package com.ev34j.mindstorm;

import com.ev34j.core.utils.Delay;
import com.ev34j.mindstorm.sensor.Ev3InfraredSensor;
import com.ev34j.mindstorm.sensor.Ev3TouchSensor;

import java.util.concurrent.TimeUnit;

public class InfraredTest {

  public static void main(String[] args) {

    Ev3TouchSensor touch = new Ev3TouchSensor("1");
    Ev3InfraredSensor ir = new Ev3InfraredSensor("4");

    System.out.println("Press touch sensor");

    while (touch.isReleased()) {
      System.out.println(String.format("Distance %% is: %s", ir.getDistancePercent()));
      Delay.millis(500);
    }

    System.out.println("Touch sensor pressed");

    // Pause to give use a chance to let go of button
    Delay.secs(3);

    System.out.println("Press touch sensor");

    while (!touch.waitUntilPressed(500, TimeUnit.MILLISECONDS)) {
      System.out.println(String.format("Distance %% is: %s", ir.getDistancePercent()));
    }

    System.out.println("Touch sensor pressed");

    System.out.println("Get below 15% on the infrared sensor");

    while (ir.getDistancePercent() > 15) {
      System.out.println(String.format("Distance %% is: %s", ir.getDistancePercent()));
      Delay.millis(500);
    }

    System.out.println("Got below 15% on the infrared sensor");
  }
}
