package com.ev34j.mindstorm.sound;

import com.ev34j.core.sound.Espeak;
import com.ev34j.core.sound.Sound;

import java.util.concurrent.atomic.AtomicReference;

public class Ev3Sound {

  private static AtomicReference<Espeak> ESPEAK_REF = new AtomicReference<>();

  private Ev3Sound() {}

  public static void beep() { Sound.getInstance().beep(); }

  public static void playTone(final int frequency, final int duration) {
    Sound.getInstance().playTone(frequency, duration, getVolume());
  }

  public static void setVolume(final int volume) { Sound.getInstance().setVolume(volume); }

  public static int getVolume() { return Sound.getInstance().getVolume(); }

  public static void sayAsEnglish(final String words) {
    if (ESPEAK_REF.get() == null)
      ESPEAK_REF.compareAndSet(null, new Espeak());

    final Espeak espeak = ESPEAK_REF.get();
    espeak.setVoice("en");
    espeak.setVolume(getVolume());
    espeak.setSpeedReading(105);
    espeak.setPitch(60);
    espeak.setMessage(words);
    espeak.say();
  }

  public static void sayAsSpanish(final String words) {
    if (ESPEAK_REF.get() == null)
      ESPEAK_REF.compareAndSet(null, new Espeak());

    final Espeak espeak = ESPEAK_REF.get();
    espeak.setVoice("es");
    espeak.setVolume(getVolume());
    espeak.setSpeedReading(200);
    espeak.setPitch(50);
    espeak.setMessage(words);
    espeak.say();
  }
}
