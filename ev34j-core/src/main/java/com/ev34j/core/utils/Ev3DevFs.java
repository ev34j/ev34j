package com.ev34j.core.utils;

import com.ev34j.core.motor.PortType;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.lang.String.format;

public class Ev3DevFs {

  private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger(Ev3DevFs.class.getName());

  public static final String EV3_ROOT = "/sys/class";

  /**
   * @param portType
   * @return an ArrayList with options from a path
   */
  public static List<File> getDevicePaths(final PortType portType) {
    final String path = portType.getPath();
    final File file = new File(path);
    if (!file.exists() || !file.isDirectory() || !file.canRead())
      throw new IllegalArgumentException(format("Invalid path for devices: %s", path));

    final File[] files = file.listFiles();
    if (files == null || files.length == 0) {
      LOGGER.warning(format("No devices available in %s", path));
      return Collections.emptyList();
    }

    LOGGER.fine(format("Available devices in %s: %s", path, files));
    return Arrays.asList(files);
  }

  public static boolean write(final String path, final byte[] value) {
    try {
      try (final OutputStream os = new FileOutputStream(path);
           final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
        baos.write(value);
        baos.writeTo(os);
        return true;
      }
    }
    catch (IOException e) {
      LOGGER.warning(format("Error when writing byte[] to %s", path));
      e.printStackTrace();
    }
    return false;
  }


  /**
   * Write a value in a file.
   *
   * @param path  File path
   * @param value value to write
   * @return A boolean value if the operation was written or not.
   */
  public static boolean write(final String path, final String value) {
    LOGGER.fine(format("echo %s > %s", value, path));
    return write(path, value.getBytes());
    /*
    try {
      try (final BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
        bw.write(value);
        bw.flush();
        return true;
      }
    }
    catch (IOException e) {
      LOGGER.warning(format("Error when writing \"%s\" to %s", value, path));
      e.printStackTrace();
    }
    return false;
    */
  }

  public static boolean write(final String filePath, final int value) { return write(filePath, "" + value); }

  public static boolean write(final String filePath, final float value) { return write(filePath, "" + value); }

  public static int readInteger(final String filePath) { return Integer.parseInt(readString(filePath)); }

  public static float readFloat(final String filePath) { return Float.parseFloat(readString(filePath)); }

  public static String readString(final String filePath) {
    LOGGER.fine(format("cat %s", filePath));
    try {
      final List<String> vals = Files.readAllLines(FileSystems.getDefault().getPath("", filePath), Charset.defaultCharset());
      if (vals.size() == 1)
        return vals.get(0);
      final StringBuilder sb = new StringBuilder();
      for (final String val : vals)
        sb.append(val).append("\n");
      return sb.toString();
    }
    catch (IOException e) {
      LOGGER.warning(format("%s %s", e.getClass().getSimpleName(), e.getMessage()));
      return "-1";
    }
  }
}
