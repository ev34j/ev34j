package examples.hardware;

import com.ev34j.core.sound.Sound;
import com.ev34j.core.utils.Delay;

import java.io.File;

//java -cp ev3-lang-java-0.3.1-SNAPSHOT.jar SoundDemo
public class SoundDemo {

  //Configuration
  private static final int    MAX_VOLUME = 100;
  private static final String filePath   = "/home/cncsounds/commander.wav";
  private final static int    ONE_SECOND = 1000;

  private static final int FREQ1     = 300;
  private static final int FREQ2     = 400;
  private static final int variation = 10;

  public static void main(String[] args) {

    Sound sound = Sound.getInstance();

    // sound.setVolume(MAX_VOLUME);
    System.out.println("Volume: " + sound.getVolume());

    File file = new File(filePath);
    sound.playSample(file);

    sound.beep();

    Delay.millis(ONE_SECOND);

    for (int i = FREQ1; i <= FREQ2; i += variation) {
      sound.playTone(i, 500, 100);
    }

    sound.playTone(300, 500);

    System.exit(0);
  }

}
