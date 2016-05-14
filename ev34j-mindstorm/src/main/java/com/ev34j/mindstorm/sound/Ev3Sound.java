package com.ev34j.mindstorm.sound;

import com.ev34j.core.sound.Espeak;
import com.ev34j.core.sound.Sound;

import java.util.concurrent.atomic.AtomicReference;

public class Ev3Sound {

  private static AtomicReference<Espeak> ESPEAK_REF = new AtomicReference<>();

  private Ev3Sound() {}

  public static void beep(final int secs, final int volume) {
    setVolume(volume);
    Sound.getInstance().beep(secs * 1000);
  }

  public static void playTone(final int frequency, final int secs, final int volume) {
    setVolume(volume);
    Sound.getInstance().playTone(frequency, secs * 1000);
  }

  public static void playNote(final Note note, final int secs, final int volume) {
    setVolume(volume);
    Sound.getInstance().playTone(note.getFrequency(), secs * 1000);
  }

  private static void setVolume(final int volume) { Sound.getInstance().setVolume(volume); }

  private static int getVolume() { return Sound.getInstance().getVolume(); }

  public static void sayAsEnglish(final String words, final int volume) {
    if (ESPEAK_REF.get() == null)
      ESPEAK_REF.compareAndSet(null, new Espeak());

    final Espeak espeak = ESPEAK_REF.get();
    espeak.setVoice("en");
    espeak.setVolume(volume);
    espeak.setSpeedReading(105);
    espeak.setPitch(60);
    espeak.setMessage(words);
    espeak.say();
  }

  public static void sayAsSpanish(final String words, final int volume) {
    if (ESPEAK_REF.get() == null)
      ESPEAK_REF.compareAndSet(null, new Espeak());

    final Espeak espeak = ESPEAK_REF.get();
    espeak.setVoice("es");
    espeak.setVolume(volume);
    espeak.setSpeedReading(200);
    espeak.setPitch(50);
    espeak.setMessage(words);
    espeak.say();
  }
}
