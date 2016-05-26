package com.ev34j.mindstorms;

import com.ev34j.mindstorms.leds.BrickPiStatusLight;
import com.ev34j.mindstorms.leds.BrickPiStatusLights;
import com.ev34j.mindstorms.time.Wait;

public class BrickPiLedTest {

  public static void main(String[] args) {

    BrickPiStatusLight left = BrickPiStatusLights.left();
    BrickPiStatusLight right = BrickPiStatusLights.right();

    left.on();
    right.on();
    Wait.forSecs(2);

    left.off();
    right.off();
    Wait.forSecs(2);

    flash(left);
    flash(right);

    left.off();
    right.off();
    System.out.println("Left light on: " + left.isOn());
    System.out.println("Right light on: " + right.isOn());
    Wait.forSecs(2);

    left.on();
    right.on();
    System.out.println("Left light on: " + left.isOn());
    System.out.println("Right light on: " + right.isOn());
    Wait.forSecs(2);
  }

  private static void flash(BrickPiStatusLight led) {
    for (int i = 0; i < 40; i++) {
      led.on();
      Wait.forMillis(20);
      led.off();
      Wait.forMillis(20);
    }
  }

}
