package com.ev34j.mindstorm;

import com.ev34j.core.battery.Battery;
import com.ev34j.mindstorm.motor.AbstractMotor;
import com.ev34j.mindstorm.sensor.AbstractColorSensor;
import com.ev34j.mindstorm.sensor.AbstractTouchSensor;
import com.ev34j.mindstorm.utils.Wait;

import static java.lang.String.format;

public class TestSupport {

  public static void batteryTest() {
    System.out.println("Battery voltage: " + Battery.getInstance().getVoltage());
    System.out.println("Battery current: " + Battery.getInstance().getBatteryCurrent());
  }

  public static void touchSensorTest(final AbstractTouchSensor button) {
    System.out.println("Press button to continue");
    button.waitUntilPressed();
    System.out.println("Pressed");

    System.out.println("Release button to continue");
    button.waitUntilReleased();
    System.out.println("Released");

    System.out.println("Bump to continue");
    button.waitUntilBumped();
    System.out.println("Bumped");

    for (int i = 0; i < 20; i++) {
      System.out.printf("%sPressed\n", !button.isPressed() ? "Not " : "");
      Wait.millis(500);
    }
  }

  public static void colorSensorTest(final AbstractColorSensor cs) {
    final int iterations = 20;
    for (int i = 0; i < iterations; i++) {
      System.out.println(format("Color ID: %s", cs.getColorId()));
      Wait.millis(500);
    }

    for (int i = 0; i < iterations; i++) {
      System.out.println(format("Reflected light: %d", cs.getReflectedLight()));
      Wait.millis(500);
    }

    for (int i = 0; i < iterations; i++) {
      System.out.println(format("RGB: %s", cs.getRgb()));
      Wait.millis(500);
    }

    for (int i = 0; i < iterations; i++) {
      System.out.println(format("Ambient light: %d", cs.getAmbientLight()));
      Wait.millis(500);
    }
  }

  public static void motorTest(final AbstractMotor motor) {

    System.out.printf("Power: %d\n", motor.getPower());
    motor.on(25);
    Wait.secs(3);
    motor.off();
    System.out.printf("Position: %d\n", motor.getPosition());

    motor.onForSecs(3, -100);
    Wait.secs(3);
    System.out.printf("Position: %d\n", motor.getPosition());
    motor.reset();
    System.out.printf("Reset: %d\n", motor.getPosition());

    /*
    motor.advanceBy(200, 100);
    Wait.delaySecs(3);
    System.out.printf("Position: %d\n", motor.getPosition());

    motor.advanceBy(-200, 100);
    Wait.delaySecs(3);
    System.out.printf("Position: %d\n", motor.getPosition());
    */
    motor.reset();
    System.out.printf("Reset for onForRotations(): %d\n", motor.getPosition());
    motor.onForRotations(3, 100);
    Wait.secs(3);
    System.out.printf("Position: %d\n", motor.getPosition());

    /*
    motor.reset();
    System.out.printf("Reset for advanceTo(): %d\n", motor.getPosition());
    motor.advanceTo(-200, 50);
    Wait.delaySecs(3);
    System.out.printf("Position: %d\n", motor.getPosition());
    */

    motor.reset();
    System.out.printf("Reset for advanceTo(): %d\n", motor.getPosition());
    motor.onForDegrees(360, 75);
    Wait.secs(3);
    System.out.printf("Position: %d\n", motor.getPosition());

/*
    motor.reset();
    System.out.printf("Reset for variableOn(): %d\n", motor.getPosition());
    motor.variableOn(-100);
    for (int i = -100; i < 100; i++) {
      motor.setVariablePower(i);
      Wait.delayMillis(100);
    }
    motor.off();
    System.out.printf("Position: %d\n", motor.getPosition());
*/
    motor.reset();
    System.out.println("\nManually turn motor");
    int currVal = motor.getPosition();
    System.out.printf("Position: %d\n", currVal);
    for (int i = 0; i < 30; i++) {
      final int tmp = motor.getPosition();
      if (tmp != currVal) {
        currVal = tmp;
        System.out.printf("Position: %d\n", currVal);
      }
      Wait.millis(500);
    }
  }
}
