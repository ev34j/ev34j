package com.ev34j.mindstorm;

import com.ev34j.mindstorm.sound.Ev3Sound;
import com.ev34j.mindstorm.sound.Note;
import com.ev34j.mindstorm.utils.Wait;

public class SoundTest {

  public static void main(String[] args) {

    Ev3Sound.say("I am a LEGO robot", 100);
    Wait.secs(1);
    for (Note note : Note.values())
      Ev3Sound.playNote(note, 1, 100);
  }
}
