package com.ev34j.core.sound;

import com.ev34j.core.utils.Shell;

import static java.lang.String.format;


public class Espeak {

  private String voice        = null;
  private int    volume       = -1;
  private int    speedReading = -1;
  private int    pitch        = -1;
  private String message      = null;
  private String filePath     = null;

  public Espeak setVoice(final String voice) {
    this.voice = voice;
    return this;
  }

  public Espeak setVolume(final int volume) {
    this.volume = volume;
    return this;
  }

  public Espeak setSpeedReading(final int speed) {
    this.speedReading = speed;
    return this;
  }

  public Espeak setPitch(final int pitch) {
    this.pitch = pitch;
    return this;
  }

  public Espeak setMessage(final String message) {
    this.message = message;
    return this;
  }

  public Espeak setFilePath(final String filePath) {
    this.filePath = filePath;
    return this;
  }

  private String getCommand() {
    final StringBuilder sb = new StringBuilder("/usr/bin/espeak ");

    if (this.voice != null)
      sb.append(format(" -v %s", this.voice));

    if (this.volume != -1)
      sb.append(format(" -a %s", this.volume));

    if (this.speedReading != -1)
      sb.append(format(" -s %s", this.speedReading));

    if (this.pitch != -1)
      sb.append(format(" -p %s", this.pitch));

    sb.append(" --stdout ");
    if (this.message != null)
      sb.append(format("\"%s\"", this.message));
    else
      sb.append(format(" -f \"%s\"", this.filePath));

    sb.append(" | aplay");

    return sb.toString();
  }

  //espeak -ves -a 200 -s 200 -p 50  --stdout -f quijote.txt | aplay
  //espeak -ves --stdout "soy un robot bueno" | aplay
  public Espeak say() {
    Shell.execute(this.getCommand());
    return this;
  }
}
