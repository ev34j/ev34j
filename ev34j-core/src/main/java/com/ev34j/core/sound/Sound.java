package com.ev34j.core.sound;

import com.ev34j.core.common.Device;
import com.ev34j.core.common.DeviceNotSupportedException;
import com.ev34j.core.common.Platform;
import com.ev34j.core.utils.Ev3DevFs;
import com.ev34j.core.utils.Shell;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.String.format;

/**
 * Class that provides access methods for the local audio device
 *
 * @author Juan Antonio Breña Moral
 */
public class Sound
    extends Device {

  private final static String SOUND_ROOT  = "/sys/devices/platform/snd-legoev3/";
  private final static String TONE_PATH   = SOUND_ROOT + "tone";
  private final static String VOLUME_PATH = SOUND_ROOT + "volume";

  private final static AtomicReference<Sound> SINGLETON = new AtomicReference<>();

  public static Sound getInstance() {
    if (SINGLETON.get() == null)
      SINGLETON.compareAndSet(null, new Sound());
    return SINGLETON.get();
  }

  private int currVolume = -1;

  private Sound() {
    if (!Platform.isEv3Brick())
      throw new DeviceNotSupportedException(this.getClass());
  }

  /**
   * Beeps once.
   */
  public void beep(final int millis) {
    Shell.execute(format("/usr/bin/beep -l %d", millis));
  }

  /**
   * Plays a tone, given its frequency and duration.
   *
   * @param freq     The frequency of the tone in Hertz (Hz).
   * @param duration The duration of the tone, in milliseconds.
   */
  public void playTone(final float frequency, final int millis) {
    Shell.execute(format("/usr/bin/beep -f %f -l %d", frequency, millis));
  }

  /**
   * Play a wav file. Must be mono, from 8kHz to 48kHz, and 8-bit or 16-bit.
   *
   * @param file the 8-bit or 16-bit PWM (WAV) sample file
   * @return The number of milliseconds the sample will play for or < 0 if
   * there is an error.
   * @throws FileNotFoundException
   */
  public void playSample(final File file) {
    Shell.execute(format("%s %s", "/usr/bin/aplay", file.toString()));
  }

  /**
   * Set the master volume level
   *
   * @param volume 0-100
   */
  public void setVolume(int volume) {
    if (volume < 0 || volume > 100)
      throw new IllegalArgumentException(format("Invalid volume value: %d. Valid values are 0 to 100.", volume));

    if (this.currVolume == volume)
      return;

    Shell.execute(format("/usr/bin/amixer set Playback,0 %d%s", volume, "%"));
    this.currVolume = volume;
  }

  /**
   * Get the current master volume level
   *
   * @return the current master volume 0-100
   */
  public int getVolume() { return Ev3DevFs.readInteger(VOLUME_PATH); }

  public int getTone() { return Ev3DevFs.readInteger(TONE_PATH); }
}
