package com.ev34j.mindstorms;

import com.ev34j.mindstorms.motor.LargeMotor;
import com.ev34j.mindstorms.motor.MediumMotor;
import com.ev34j.mindstorms.sensor.Ev3InfraredSensor;
import com.ev34j.mindstorms.sensor.NxtTouchSensor;
import com.ev34j.mindstorms.sensor.NxtUltrasonicSensor;
import com.ev34j.mindstorms.time.Wait;

import static java.lang.String.format;

public class BrickPiTest {

  public static void main(String[] args) {

    TestSupport.batteryTest();

    TestSupport.touchSensorTest(new NxtTouchSensor("S4"));

    TestSupport.motorTest(new LargeMotor("MA"));
    TestSupport.motorTest(new MediumMotor("MA"));

    final Ev3InfraredSensor ir = new Ev3InfraredSensor("S3");
    for (int i = 0; i < 100; i++) {
      System.out.println(format("IR: %s", ir.getDistancePercent()));
      Wait.forMillis(100);
    }

    final NxtUltrasonicSensor us = new NxtUltrasonicSensor("S4");
    for (int i = 0; i < 100; i++) {
      System.out.println(format("US: %s", us.getDistanceInches()));
      Wait.forMillis(100);
    }
  }

}
