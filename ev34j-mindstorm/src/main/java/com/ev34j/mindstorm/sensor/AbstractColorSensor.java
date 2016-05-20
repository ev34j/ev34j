package com.ev34j.mindstorm.sensor;

import com.ev34j.core.sensor.ColorSensor;

public abstract class AbstractColorSensor
    extends AbstractSensor<ColorSensor> {


  protected AbstractColorSensor(final ColorSensor sensor) {
    super(sensor);
  }

  public ColorType getColorId() {
    this.setSensorMode(this.getSensor().getColorIdMode())
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return ColorType.findByValue((int) this.getSample(0));
  }

  public int getReflectedLight() {
    this.setSensorMode(this.getSensor().getReflectedMode())
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return (int) this.getSample(0);
  }

  public RgbValue getRgb() {
    this.setSensorMode(this.getSensor().getRGBMode())
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return new RgbValue((int) this.getSample(0), (int) this.getSample(1), (int) this.getSample(2));
  }

  public int getAmbientLight() {
    this.setSensorMode(this.getSensor().getAmbientMode())
        .getSensorMode()
        .fetchSample(this.getSamples(), 0);
    return (int) this.getSample(0);
  }
}
