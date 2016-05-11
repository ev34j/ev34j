package com.ev34j.core.buttons;

import com.ev34j.core.common.Device;
import com.ev34j.core.common.DeviceException;
import com.ev34j.core.common.DeviceNotSupportedException;
import com.ev34j.core.common.Platform;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

import static java.lang.String.format;

public class Buttons
    extends Device {

  private static final AtomicReference<Buttons> SINGLETON = new AtomicReference<>();

  public static Buttons getInstance() {
    if (SINGLETON.get() == null)
      SINGLETON.compareAndSet(null, new Buttons());
    return SINGLETON.get();
  }

  private static class ButtonPress {
    private final short type;
    private final short code;
    private final int   value;

    private ButtonPress(final FileInputStream is)
        throws IOException {
      final byte[] buf = new byte[16];
      final int retval = is.read(buf);
      if (retval == -1)
        throw new DeviceException("Invalid file read");

      // The first 8 bytes are the time stamp (2 unsigned 64-bit integers, seconds and microseconds),
      // the next 2 bytes are the type (unsigned 16-bit integer),
      // the next 2 bytes are the code (unsigned 16-bit integer)
      // and the last 4 bytes are the value (unsigned 32-bit integer)

      this.type = ByteBuffer.wrap(Arrays.copyOfRange(buf, 8, 10)).order(ByteOrder.LITTLE_ENDIAN).getShort();
      this.code = ByteBuffer.wrap(Arrays.copyOfRange(buf, 10, 12)).order(ByteOrder.LITTLE_ENDIAN).getShort();
      this.value = ByteBuffer.wrap(Arrays.copyOfRange(buf, 12, 16)).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    private short getType() { return this.type; }

    private short getCode() { return this.code; }

    private int getValue() { return this.value; }
  }

  private final File file = new File("/dev/input/event0");

  private Buttons() {
    if (!Platform.isEv3Brick())
      throw new DeviceNotSupportedException(this.getClass());
  }

  public ButtonType getButtonPress() {
    try (final FileInputStream is = new FileInputStream(this.file)) {
      // Each button press requires 4 reads -- 2 up and 2 down.
      final ButtonPress press1 = new ButtonPress(is);
      final ButtonPress press2 = new ButtonPress(is);
      final ButtonPress release1 = new ButtonPress(is);
      final ButtonPress release2 = new ButtonPress(is);
      return ButtonType.findByValue(press1.getCode());
    }
    catch (IOException e) {
      e.printStackTrace();
      throw new DeviceException(format("IOException: %s", e.getMessage()), e);
    }
  }

  /*
  public interface LibC
      extends Library {
    LibC libC = (LibC) Native.loadLibrary("c", LibC.class);
    int open(String fname, int mode);
    int close(int fd);
    int ioctl(int fd, int request, Object... args);
  }
  */

  /*
    int fd = LibC.libC.open("/dev/input/by-path/platform-gpio-keys.0-event", O_RDWR);
    if (fd < 0) {
      System.err.println("Error opening library");
      System.exit(1);
    }

    for (int i = 0; i < 30; i++) {
      byte[] buf = new byte[BUF_LEN];
      final int val = LibC.libC.ioctl(fd, EVIOCGKEY(buf.length), buf);
      System.out.println(format("Val: %d", val));
      Delay.delaySecs(1);
    }

    LibC.libC.close(fd);
  */

  /*
  private static int O_RDWR        = 0x00000002;
  private static int KEY_UP        = 103;
  private static int KEY_DOWN      = 108;
  private static int KEY_LEFT      = 105;
  private static int KEY_RIGHT     = 106;
  private static int KEY_ENTER     = 28;
  private static int KEY_BACKSPACE = 14;

  private static int KEY_MAX = 0x2ff;

  //Path path = Paths.get("/dev/input/by-path/platform-gpio-keys.0-event");


  private static int EVIOCGKEY(int length) {
    return 2 << (14 + 8 + 8) | length << (8 + 8) | (int) 'E' << 8 | 0x18;
  }

  private static final int BUF_LEN = (KEY_MAX + 7) / 8;
  */

}
