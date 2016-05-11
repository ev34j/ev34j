package examples.sensors;

import com.ev34j.core.battery.Battery;
import com.ev34j.core.sensor.GyroSensorEv3;
import com.ev34j.core.sensor.SensorMode;
import com.ev34j.core.sensor.SensorPort;
import com.ev34j.core.sensor.SensorSetting;
import com.ev34j.core.utils.Delay;

//java -cp ev3-lang-java-0.2-SNAPSHOT.jar GyroSensorDemo
public class GyroSensorDemo {

  //Robot Configuration
  private static final GyroSensorEv3 touch1 = new GyroSensorEv3(GyroSensorEv3.class,
                                                                SensorPort.S3,
                                                                SensorSetting.EV3_GYRO);

  //Configuration
  private static final int HALF_SECOND = 500;

  public static void main(String[] args) {

    final SensorMode sensorMode = touch1.getAngleDegreesMode();
    int value = 0;

    //Robot control loop
    final int iteration_threshold = 50;
    for (int i = 0; i <= iteration_threshold; i++) {

      float[] sample = new float[sensorMode.sampleSize()];
      sensorMode.fetchSample(sample, 0);
      value = (int) sample[0];

      System.out.println("Iteration: " + i);
      System.out.println("Battery: " + Battery.getInstance().getVoltage());
      System.out.println("Touch: " + value);
      System.out.println();

      Delay.delayMillis(HALF_SECOND);
    }

  }

}
