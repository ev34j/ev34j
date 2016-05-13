package com.ev34j.mindstorm;

import com.ev34j.core.utils.Delay;
import com.ev34j.mindstorm.leds.Ev3StatusLight;
import com.ev34j.mindstorm.leds.Ev3StatusLights;
import com.ev34j.mindstorm.sound.Ev3Sound;

public class LedTest {

  public static void main(String[] args) {

    Ev3StatusLight left = Ev3StatusLights.left();
    Ev3StatusLight right = Ev3StatusLights.right();

    Ev3Sound.setVolume(100);
    Ev3Sound.sayAsEnglish("Volume is: " + Ev3Sound.getVolume());

    Ev3Sound.sayAsEnglish("Red");
    left.red();
    right.red();
    Delay.secs(2);

    Ev3Sound.setVolume(25);
    Ev3Sound.sayAsEnglish("Volume is: " + Ev3Sound.getVolume());
    Ev3Sound.sayAsEnglish("Green");
    left.green();
    right.green();
    Delay.secs(2);

    Ev3Sound.setVolume(50);
    Ev3Sound.sayAsEnglish("Volume is: " + Ev3Sound.getVolume());
    Ev3Sound.sayAsEnglish("Orange");
    left.orange();
    right.orange();
    Delay.secs(2);

    Ev3Sound.setVolume(100);
    Ev3Sound.sayAsEnglish("Volume is: " + Ev3Sound.getVolume());
    Ev3Sound.sayAsEnglish("Off");
    left.off();
    right.off();
    Delay.secs(2);
  }

}
