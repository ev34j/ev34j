package com.ev34j.mindstorm.sensor;

import com.ev34j.core.sensor.SensorPort;
import com.ev34j.core.sensor.SensorSetting;
import com.ev34j.core.sensor.TouchSensor;
import com.ev34j.core.utils.Delay;

import java.util.concurrent.TimeUnit;

public abstract class AbstractTouchSensor
    extends AbstractSensor<TouchSensor> {

  protected AbstractTouchSensor(final Class<?> deviceClass, final String portName, final SensorSetting sensorSetting) {
    super((new TouchSensor(deviceClass,
                           SensorPort.findByPort(portName),
                           sensorSetting.getConnnectionType(),
                           sensorSetting.getSensorType())));
  }

  public boolean isPressed() { return this.getSensor().isPressed(); }

  public boolean isReleased() { return !this.isPressed(); }

  public void waitUntilPressed() {
    while (!this.isPressed()) {
      Delay.millis(AbstractSensor.getSensorPollingWaitMillis());
    }
  }

  public void waitUntilReleased() {
    while (this.isPressed()) {
      Delay.millis(AbstractSensor.getSensorPollingWaitMillis());
    }
  }

  public void waitUntilBumped() {
    this.waitUntilPressed();
    this.waitUntilReleased();
  }

  public boolean waitUntilPressed(final int time, final TimeUnit timeUnit) {
    return this.waitUntilPressed(System.currentTimeMillis(), time, timeUnit);
  }

  private boolean waitUntilPressed(final long start, final int time, final TimeUnit timeUnit) {
    while (!this.isPressed()) {
      if (System.currentTimeMillis() - start > timeUnit.toMillis(time))
        return false;
      else
        Delay.millis(AbstractSensor.getSensorPollingWaitMillis());
    }
    return true;
  }

  public boolean waitUntilReleased(final int time, final TimeUnit timeUnit) {
    return this.waitUntilReleased(System.currentTimeMillis(), time, timeUnit);
  }

  private boolean waitUntilReleased(final long start, final int time, final TimeUnit timeUnit) {
    while (this.isPressed()) {
      if (System.currentTimeMillis() - start > timeUnit.toMillis(time))
        return false;
      else
        Delay.millis(AbstractSensor.getSensorPollingWaitMillis());
    }
    return true;
  }

  public boolean waitUntilBumped(final int time, final TimeUnit timeUnit) {
    final long start = System.currentTimeMillis();
    final boolean pressed = this.waitUntilPressed(start, time, timeUnit);
    return pressed && this.waitUntilReleased(start, time, timeUnit);
  }
}
