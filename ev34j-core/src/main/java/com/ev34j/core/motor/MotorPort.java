package com.ev34j.core.motor;

import com.ev34j.core.common.Platform;

import static java.lang.String.format;

/**
 * Basic interface for EV3 motor ports.
 *
 * @author Juan Antonio Breña Moral
 */
public enum MotorPort {

  A("A", "outA", "MA", "ttyAMA0:MA", "BM1", "pistorms:BBM1"),
  B("B", "outB", "MB", "ttyAMA0:MB", "BM2", "pistorms:BBM2"),
  C("C", "outC", "MC", "ttyAMA0:MC", "AM2", "pistorms:BAM2"),
  D("D", "outD", "MD", "ttyAMA0:MD", "AM1", "pistorms:BAM1");

  private final String ev3PortName;
  private final String ev3PortAddress;
  private final String brickPiPortName;
  private final String brickPiPortAddress;
  private final String piStormPortName;
  private final String piStormPortAddress;

  MotorPort(final String ev3PortName,
            final String ev3PortAddress,
            final String brickPiPortName,
            final String brickPiPortAddress,
            final String piStormPortName,
            final String piStormPortAddress) {
    this.ev3PortName = ev3PortName;
    this.ev3PortAddress = ev3PortAddress;
    this.brickPiPortName = brickPiPortName;
    this.brickPiPortAddress = brickPiPortAddress;
    this.piStormPortName = piStormPortName;
    this.piStormPortAddress = piStormPortAddress;
  }

  public String getPortName() {
    switch (Platform.getPlatform()) {
      case EV3BRICK:
        return this.ev3PortName;
      case BRICKPI:
        return this.brickPiPortName;
      case PISTORMS:
        return this.piStormPortName;
      default:
        throw new IllegalStateException(format("Invalid platform type: %s", Platform.getPlatform()));
    }
  }

  public String getPortAddress() {
    switch (Platform.getPlatform()) {
      case EV3BRICK:
        return this.ev3PortAddress;
      case BRICKPI:
        return this.brickPiPortAddress;
      case PISTORMS:
        return this.piStormPortAddress;
      default:
        throw new IllegalStateException(format("Invalid platform type: %s", Platform.getPlatform()));
    }
  }

  public static MotorPort findByPort(final String port) {
    if (port == null)
      throw new IllegalArgumentException("Invalid port: null");
    for (final MotorPort motorPort : MotorPort.values()) {
      if (motorPort.getPortName().equalsIgnoreCase(port))
        return motorPort;
    }
    throw new IllegalArgumentException(format("Invalid port: %s", port));
  }
}
