package com.ev34j.mindstorm.sensor;

import com.ev34j.core.sensor.AllSignaturesValues;
import com.ev34j.core.sensor.ColorCodeValues;
import com.ev34j.core.sensor.SensorPort;
import com.ev34j.core.sensor.SignatureValues;
import com.ev34j.core.sensor.VisionSensor;

import static com.ev34j.core.sensor.SensorSetting.PIXY;
import static java.lang.String.format;

public class Ev3PixySensor
    extends AbstractSensor<VisionSensor> {

  public Ev3PixySensor(final int portNum) {
    this("" + portNum);
  }

  public Ev3PixySensor(final String portName) {
    super(new VisionSensor(Ev3PixySensor.class,
                           SensorPort.findByPort(portName),
                           PIXY.getDriverType(),
                           PIXY.getModuleType()));
  }

  public AllSignaturesValues getAllSignatures() {
    this.setSensorMode(this.getSensor().getAllSignaturesMode())
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return new AllSignaturesValues((int) this.getSample(0),
                                   (int) this.getSample(1),
                                   (int) this.getSample(2),
                                   (int) this.getSample(3),
                                   (int) this.getSample(4),
                                   (int) this.getSample(5),
                                   (int) this.getSample(6));
  }

  public SignatureValues getSignature(final int signature) {
    if (signature < 1 || signature > 7)
      throw new IllegalArgumentException(format("Invalid signature number: %d", signature));
    this.setSensorMode(this.getSensor().getSingleSignatureMode(signature - 1))
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return new SignatureValues((int) this.getSample(0),
                               (int) this.getSample(1),
                               (int) this.getSample(2),
                               (int) this.getSample(3),
                               (int) this.getSample(4));
  }


  public ColorCodeValues getColorCode() {
    this.setSensorMode(this.getSensor().getColorCodeMode())
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return new ColorCodeValues((int) this.getSample(0),
                               (int) this.getSample(1),
                               (int) this.getSample(2),
                               (int) this.getSample(3),
                               (int) this.getSample(4),
                               (int) this.getSample(5));
  }

  public int getAngle() {
    this.setSensorMode(this.getSensor().getAngleMode())
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return (int) this.getSample(0);
  }
}
