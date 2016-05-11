package com.ev34j.core.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class Ev3LogFormatter
    extends Formatter {

  private final Date             date       = new Date();
  private final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy-HH:mm:ss", Locale.US);

  @Override
  public synchronized String format(final LogRecord record) {
    this.date.setTime(record.getMillis());
    final String logLevel = record.getLevel().getName();
    final String message = this.formatMessage(record);
    return String.format("%s %s %s\n", this.dateFormat.format(this.date), logLevel, message);
  }
}
