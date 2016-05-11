package com.ev34j.mindstorm.sensor;

import com.ev34j.core.sensor.UltrasonicSensor;

public abstract class AbstractUltrasonicSensor
    extends AbstractSensor<UltrasonicSensor> {

  protected AbstractUltrasonicSensor(final UltrasonicSensor sensor) {
    super(sensor);
  }

  public float getDistanceCms() {
    this.setSensorMode(this.getSensor().getContinuousDistanceCmsMode())
        .getSensorMode().fetchSample(this.getSamples(), 0);
    return (int) this.getSample(0);
  }

  public float getDistanceInches() {
    this.setSensorMode(this.getSensor().getContinuousDistanceInchesMode())
        .getSensorMode().fetchSample(this.getSamples(), 0);
    return (int) this.getSample(0);
  }

}
