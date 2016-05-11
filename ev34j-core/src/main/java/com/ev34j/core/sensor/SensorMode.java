package com.ev34j.core.sensor;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.lang.String.format;

/**
 * @author Juan Antonio Breña Moral
 */
public abstract class SensorMode {

  private final List<String> sensorPaths = new ArrayList<>();

  private final ModeType modeType;

  protected SensorMode(final ModeType modeType, final File devicePath, final SensorValue... sensorValues) {
    this.modeType = modeType;
    for (final SensorValue sensorValue : sensorValues)
      this.sensorPaths.add(format("%s/%s", devicePath, sensorValue.getValue()));
  }

  public abstract void fetchSample(float[] sample, int offset);

  public int sampleSize() { return 1; }

  protected ModeType getModeType() { return this.modeType; }

  protected String getSensorPath(final int index) { return this.sensorPaths.get(index); }
}
