package examples.sensors;

import com.ev34j.core.sensor.ColorSensor;
import com.ev34j.core.sensor.SensorMode;
import com.ev34j.core.sensor.SensorPort;
import com.ev34j.core.sensor.SensorSetting;
import com.ev34j.core.utils.Delay;

//java -cp ev3-lang-java-0.2-SNAPSHOT.jar ColorSensorDemo
public class ColorSensorDemo {

  //Robot Configuration
  private static final ColorSensor color1 = new ColorSensor(SensorPort.S3, SensorSetting.EV3_COLOR);

  //Configuration
  private static final int HALF_SECOND = 500;

  public static void main(String[] args) {

    //Red Mode
    SensorMode sensorMode = color1.getReflectedMode();

    int sampleSize = sensorMode.sampleSize();
    float[] sample = new float[sampleSize];

    // Takes some samples and prints them
    for (int i = 0; i < 10; i++) {
      sensorMode.fetchSample(sample, 0);
      System.out.println("N=" + i + " Sample=" + (int) sample[0]);
      Delay.delayMillis(HALF_SECOND);
    }

    //Color ID
    sensorMode = color1.getColorIdMode();

    sampleSize = sensorMode.sampleSize();
    sample = new float[sampleSize];

    // Takes some samples and prints them
    for (int i = 0; i < 10; i++) {
      sensorMode.fetchSample(sample, 0);
      System.out.println("N=" + i + " Sample=" + (int) sample[0]);

      Delay.delayMillis(HALF_SECOND);
    }

    //Ambient Mode
    sensorMode = color1.getAmbientMode();

    sampleSize = sensorMode.sampleSize();
    sample = new float[sampleSize];

    // Takes some samples and prints them
    for (int i = 0; i < 10; i++) {
      sensorMode.fetchSample(sample, 0);
      System.out.println("N=" + i + " Sample=" + (int) sample[0]);

      Delay.delayMillis(HALF_SECOND);
    }

    //RGB
    sensorMode = color1.getRGBMode();

    sampleSize = sensorMode.sampleSize();
    sample = new float[sampleSize];

    // Takes some samples and prints them
    for (int i = 0; i < 10; i++) {
      sensorMode.fetchSample(sample, 0);
      System.out.println("N=" + i + " Sample=" + (int) sample[0]);
      System.out.println("N=" + i + " Sample=" + (int) sample[1]);
      System.out.println("N=" + i + " Sample=" + (int) sample[2]);

      Delay.delayMillis(HALF_SECOND);
    }

  }

}
