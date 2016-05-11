package com.ev34j.core.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Shell {

  public static String execute(final String command) {
    return execute(new String[] {command});
  }

  public static String execute(final String[] command) {
    final StringBuilder sb = new StringBuilder();
    try {
      final Process p = Runtime.getRuntime().exec(command);
      p.waitFor();

      try (final BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
        String line;
        while ((line = reader.readLine()) != null) {
          sb.append(line)
            .append("\n");
        }
      }
    }
    catch (Exception e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
    return sb.toString();
  }
}