package com.ev34j.mindstorm.utils;

import com.ev34j.core.utils.Delay;

public class Wait {

  public static void forSecs(final int period) { Delay.secs(period); }

  public static void forMillis(final int period) { Delay.millis(period); }
}
