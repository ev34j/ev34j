package com.ev34j.core.sensor;

import com.ev34j.core.common.Platform;

import static java.lang.String.format;

/**
 * Basic interface for EV3 sensor ports.
 *
 * @author Andy Shaw
 * @author Juan Antonio Breña Moral
 */
public enum SensorPort {

  S1("1", "in1", "S1", "ttyAMA0:S1", "BBS1", "pistorms:BBS1"),
  S2("2", "in2", "S2", "ttyAMA0:S2", "BBS2", "pistorms:BBS2"),
  S3("3", "in3", "S3", "ttyAMA0:S3", "BAS2", "pistorms:BAS2"),
  S4("4", "in4", "S4", "ttyAMA0:S4", "BAS1", "pistorms:BAS1");

  private final String ev3PortName;
  private final String ev3PortAddress;
  private final String brickPiPortName;
  private final String brickPiPortAddress;
  private final String piStormPortName;
  private final String piStormPortAddress;

  SensorPort(final String ev3PortName,
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

  public static SensorPort findByPort(final String port) {
    if (port == null)
      throw new IllegalArgumentException("Invalid port: null");
    for (final SensorPort sensorPort : SensorPort.values()) {
      if (sensorPort.getPortName().equalsIgnoreCase(port))
        return sensorPort;
    }
    throw new IllegalArgumentException(format("Invalid port: %s", port));
  }

  public static boolean isSensorAddress(final String address) {
    if (address == null)
      throw new IllegalArgumentException("Invalid address: null");
    for (final SensorPort sensorPort : SensorPort.values()) {
      if (sensorPort.getPortAddress().equalsIgnoreCase(address))
        return true;
    }
    return false;
  }
}
