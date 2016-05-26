package com.ev34j.mindstorms.sensor;

import com.ev34j.core.sensor.GenericSensor;
import com.ev34j.core.sensor.SensorMode;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public abstract class AbstractSensor<T extends GenericSensor> {

  private static final AtomicInteger SENSOR_POLL_WAIT_MILLIS = new AtomicInteger(100);

  static int getSensorPollingWaitMillis() { return SENSOR_POLL_WAIT_MILLIS.get(); }

  public static void setSensorPollingWaitMillis(final int waitMillis) { SENSOR_POLL_WAIT_MILLIS.set(waitMillis); }

  private final AtomicReference<SensorMode> sensorMode = new AtomicReference<>();

  private float[] samples = null;

  private final T sensor;

  protected AbstractSensor(final T sensor) {
    this.sensor = sensor;
  }

  protected T getSensor() { return this.sensor; }

  protected float[] getSamples() { return this.samples; }

  protected float getSample(final int index) { return this.samples[index]; }

  protected AbstractSensor<T> setSensorMode(final SensorMode sensorMode) {
    // Check is new mode is same as current mode
    if (this.getSensorMode() != sensorMode) {
      this.sensorMode.set(sensorMode);
      final int size = this.getSensorMode().sampleSize();
      if (this.samples == null || this.getSamples().length != size)
        this.samples = new float[size];
    }

    // Clear sample values
    for (int i = 0; i < this.getSamples().length; i++)
      this.samples[i] = Float.MIN_VALUE;

    return this;
  }

  protected SensorMode getSensorMode() { return this.sensorMode.get(); }
}
