package com.ev34j.mindstorm;

import com.ev34j.mindstorm.leds.Ev3StatusLight;
import com.ev34j.mindstorm.leds.Ev3StatusLights;
import com.ev34j.mindstorm.sound.Ev3Sound;
import com.ev34j.mindstorm.utils.Wait;

public class LedTest {

  public static void main(String[] args) {

    Ev3StatusLight left = Ev3StatusLights.left();
    Ev3StatusLight right = Ev3StatusLights.right();

    Ev3Sound.say("Red", 100);
    left.red();
    right.red();
    Wait.forSecs(2);

    Ev3Sound.say("Green", 25);
    left.green();
    right.green();
    Wait.forSecs(2);

    Ev3Sound.say("Orange", 50);
    left.orange();
    right.orange();
    Wait.forSecs(2);

    Ev3Sound.say("Off", 100);
    left.off();
    right.off();
    Wait.forSecs(2);
  }

}
