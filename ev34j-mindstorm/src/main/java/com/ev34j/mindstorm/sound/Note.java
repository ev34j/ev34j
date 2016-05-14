package com.ev34j.mindstorm.sound;

public enum Note {

  B6(1975.53F),
  AS6(1864.66F),
  A6(1760.00F),
  GS6(1661.22F),
  G6(1567.98F),
  FS6(1479.98F),
  F6(1396.91F),
  E6(1318.51F),
  DS6(1244.51F),
  D6(1174.66F),
  CS6(1108.73F),
  C6(1046.50F),
  B5(987.767F),
  AS5(932.328F),
  A5(880.000F),
  GS5(830.609F),
  G5(783.991F),
  FS5(739.989F),
  F5(698.456F),
  E5(659.255F),
  DS5(622.254F),
  D5(587.330F),
  CS5(554.365F),
  C5(523.251F),
  B4(493.883F),
  AS4(466.164F),
  A4(440.000F),
  GS4(415.305F),
  G4(391.995F),
  FS4(369.994F),
  F4(349.228F),
  E4(329.628F),
  DS4(311.127F),
  D4(293.665F),
  CS4(277.183F),
  C4(261.626F);

  private final float frequency;

  Note(final float frequency) {
    this.frequency = frequency;
  }

  public float getFrequency() { return this.frequency; }
}
