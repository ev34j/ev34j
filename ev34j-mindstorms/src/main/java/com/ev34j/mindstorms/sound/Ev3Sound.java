package com.ev34j.mindstorms.sound;

import com.ev34j.core.sound.Espeak;
import com.ev34j.core.sound.ev3.Sound;

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

  public static void say(final String words, final int volume) {
    sayAsEnglish(words, volume);
  }

  private static Espeak getEspeak() {
    if (ESPEAK_REF.get() == null)
      ESPEAK_REF.compareAndSet(null, new Espeak());
    return ESPEAK_REF.get();
  }

  private static void sayAsEnglish(final String words, final int volume) {
    getEspeak().setVoice("en")
               .setVolume(volume)
               .setSpeedReading(105)
               .setPitch(60)
               .setMessage(words)
               .say();
  }

  private static void sayAsSpanish(final String words, final int volume) {
    getEspeak().setVoice("es")
               .setVolume(volume)
               .setSpeedReading(200)
               .setPitch(50)
               .setMessage(words)
               .say();
  }
}
