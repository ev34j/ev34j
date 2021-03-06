package com.ev34j.core.utils;

/**
 * Simple collection of time delay routines that are non interruptable.
 *
 * @author andy
 */
public class Delay {

  public static void secs(long period) { millis(period * 1000); }

  /**
   * Wait for the specified number of milliseconds.
   * Delays the current thread for the specified period of time. Can not
   * be interrupted (but it does preserve the interrupted state).
   *
   * @param period time to wait in ms
   */
  public static void millis(final long period) {
    if (period <= 0)
      return;

    final long start = System.currentTimeMillis();
    boolean interrupted = false;
    do {
      try {
        Thread.sleep(period);
      }
      catch (InterruptedException ie) {
        interrupted = true;
      }
    } while (System.currentTimeMillis() - start <= 0);

    if (interrupted)
      Thread.currentThread().interrupt();
  }

}
