package com.ev34j.mindstorms;

import com.ev34j.mindstorms.buttons.Ev3Buttons;
import com.ev34j.mindstorms.motor.LargeMotor;
import com.ev34j.mindstorms.sensor.Ev3ColorSensor;
import com.ev34j.mindstorms.sensor.Ev3InfraredSensor;
import com.ev34j.mindstorms.sensor.Ev3TouchSensor;
import com.ev34j.mindstorms.sensor.NxtUltrasonicSensor;
import com.ev34j.mindstorms.sensor.NxtV2ColorSensor;
import com.ev34j.mindstorms.time.Wait;

import static java.lang.String.format;

public class Ev3SensorTest {

  public static void main(String[] args) {

    TestSupport.batteryTest();

    TestSupport.touchSensorTest(new Ev3TouchSensor("1"));

    for (int i = 0; i < 5; i++) {
      System.out.println("Press a button");
      System.out.println(format("%s was pressed", Ev3Buttons.waitForButtonPress()));
    }

    TestSupport.motorTest(new LargeMotor("A"));

    final Ev3ColorSensor cs = new Ev3ColorSensor("2");
    TestSupport.colorSensorTest(cs);

    final NxtV2ColorSensor nxtcs = new NxtV2ColorSensor("2");
    TestSupport.colorSensorTest(nxtcs);

    final Ev3InfraredSensor ir = new Ev3InfraredSensor("2");
    for (int i = 0; i < 100; i++) {
      System.out.println(format("IR: %s", ir.getDistancePercent()));
      Wait.forMillis(100);
    }

    final NxtUltrasonicSensor us = new NxtUltrasonicSensor("2");
    for (int i = 0; i < 100; i++) {
      System.out.println(format("US: %s", us.getDistanceInches()));
      Wait.forMillis(100);
    }

  }
}
