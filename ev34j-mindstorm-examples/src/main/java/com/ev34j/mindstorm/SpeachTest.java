package com.ev34j.mindstorm;

import com.ev34j.core.utils.Delay;
import com.ev34j.mindstorm.sound.Ev3Sound;

public class SpeachTest {

  public static void main(String[] args) {

    Ev3Sound.sayAsEnglish("I am a LEGO robot");
    Delay.delaySecs(1);
    Ev3Sound.sayAsSpanish("Soy un robot LEGO");
  }
}
