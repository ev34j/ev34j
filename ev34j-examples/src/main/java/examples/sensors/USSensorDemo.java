package examples.sensors;

import com.ev34j.core.battery.Battery;
import com.ev34j.core.sensor.SensorMode;
import com.ev34j.core.sensor.SensorPort;
import com.ev34j.core.sensor.SensorSetting;
import com.ev34j.core.sensor.UltrasonicSensor;
import com.ev34j.core.utils.Delay;

//java -cp ev3-lang-java-0.2-SNAPSHOT.jar ev3dev.java.examples.sensors.USSensorDemo
public class USSensorDemo {

  //Robot Configuration
  private static final UltrasonicSensor us1 = new UltrasonicSensor(SensorPort.S3, SensorSetting.EV3_US);

  //Configuration
  private static final int HALF_SECOND = 500;

  public static void main(String[] args) {

    final SensorMode sensorMode = us1.getDistanceCmMode(true);
    int distanceValue;

    //Robot control loop
    final int iteration_threshold = 50;
    for (int i = 0; i <= iteration_threshold; i++) {

      float[] sample = new float[sensorMode.sampleSize()];
      sensorMode.fetchSample(sample, 0);
      distanceValue = (int) sample[0];

      System.out.println("Iteration: " + i);
      System.out.println("Battery: " + Battery.getInstance().getVoltage());
      System.out.println("Touch: " + distanceValue);
      System.out.println();

      Delay.delayMillis(HALF_SECOND);
    }

  }

}
