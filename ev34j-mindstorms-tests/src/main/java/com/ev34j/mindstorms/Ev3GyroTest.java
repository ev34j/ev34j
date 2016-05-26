package com.ev34j.mindstorms;

import com.ev34j.mindstorms.sensor.Ev3GyroSensor;

import static java.lang.String.format;

public class Ev3GyroTest {

  public static void main(String[] args)
      throws InterruptedException {

    final Ev3GyroSensor gyro = new Ev3GyroSensor("2");

    gyro.reset();

    for (int i = 0; i < 100; i++) {
      System.out.println(format("Angle degrees: %d", gyro.getAngleDegrees()));
      Thread.sleep(500);
    }

  }
}
