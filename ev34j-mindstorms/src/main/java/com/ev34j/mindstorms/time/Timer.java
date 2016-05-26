package com.ev34j.mindstorms.time;

import java.util.concurrent.TimeUnit;

public class Timer {

  private final long startTime = System.currentTimeMillis();

  private final long durationMillis;

  public Timer(final int duration) {
    this(duration, TimeUnit.SECONDS);
  }

  public Timer(final long duration, final TimeUnit timeUnit) {
    this.durationMillis = TimeUnit.MILLISECONDS.convert(duration, timeUnit);
  }

  public boolean isElapsed() { return this.elapsedMillis() > this.durationMillis(); }

  public long durationMillis() { return this.durationMillis; }

  public long elapsedMillis() { return System.currentTimeMillis() - this.startTime; }

  public long remainingMillis() { return this.durationMillis() - this.elapsedMillis(); }
}
