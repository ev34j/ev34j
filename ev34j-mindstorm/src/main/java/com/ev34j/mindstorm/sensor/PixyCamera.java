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

  public SignatureValues getSignature1Value() {
    this.setSensorMode(this.getSensor().getSig1Mode())
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return this.newSignatureValues();
  }

  public SignatureValues getSignature2Values() {
    this.setSensorMode(this.getSensor().getSig2Mode())
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return this.newSignatureValues();
  }

  public SignatureValues getSignature3Values() {
    this.setSensorMode(this.getSensor().getSig3Mode())
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return this.newSignatureValues();
  }

  public SignatureValues getSignature4Values() {
    this.setSensorMode(this.getSensor().getSig4Mode())
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return this.newSignatureValues();
  }

  public SignatureValues getSignature5Values() {
    this.setSensorMode(this.getSensor().getSig5Mode())
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return this.newSignatureValues();
  }

  public SignatureValues getSignature6Values() {
    this.setSensorMode(this.getSensor().getSig6Mode())
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return this.newSignatureValues();
  }

  public SignatureValues getSignature7Values() {
    this.setSensorMode(this.getSensor().getSig7Mode())
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return this.newSignatureValues();
  }

}
