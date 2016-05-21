package com.ev34j.mindstorm.sensor;

import com.ev34j.core.sensor.PixySensor;
import com.ev34j.core.sensor.SensorPort;

import static com.ev34j.core.sensor.SensorSetting.PIXY;

public class PixyCamera
    extends AbstractSensor<PixySensor> {

  public PixyCamera(final int portNum) {
    this("" + portNum);
  }

  public PixyCamera(final String portName) {
    super(new PixySensor(PixyCamera.class,
                         SensorPort.findByPort(portName),
                         PIXY.getDriverType(),
                         PIXY.getModuleType()));
  }

  private SignatureValues newSignatureValues() {
    return new SignatureValues((int) this.getSample(0),
                               (int) this.getSample(1),
                               (int) this.getSample(2),
                               (int) this.getSample(3),
                               (int) this.getSample(4));
  }

  public int getCount(final int signature) {
    this.setSensorMode(this.getSensor().getSigMode(signature - 1))
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return (int) this.getSample(0);
  }

  public int getX(final int signature) {
    this.setSensorMode(this.getSensor().getSigMode(signature - 1))
        .getSensorMode()
        .fetchSample(this.getSamples(), 1);
    return (int) this.getSample(0);
  }

  public int getY(final int signature) {
    this.setSensorMode(this.getSensor().getSigMode(signature - 1))
        .getSensorMode()
        .fetchSample(this.getSamples(), 2);
    return (int) this.getSample(0);
  }

  public int getWidth(final int signature) {
    this.setSensorMode(this.getSensor().getSigMode(signature - 1))
        .getSensorMode()
        .fetchSample(this.getSamples(), 3);
    return (int) this.getSample(0);
  }

  public int getHeight(final int signature) {
    this.setSensorMode(this.getSensor().getSigMode(signature - 1))
        .getSensorMode()
        .fetchSample(this.getSamples(), 4);
    return (int) this.getSample(0);
  }


}
