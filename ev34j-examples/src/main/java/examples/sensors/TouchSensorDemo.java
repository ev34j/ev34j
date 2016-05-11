package examples.sensors;

import com.ev34j.core.battery.Battery;
import com.ev34j.core.sensor.ConnnectionType;
import com.ev34j.core.sensor.SensorMode;
import com.ev34j.core.sensor.SensorPort;
import com.ev34j.core.sensor.SensorType;
import com.ev34j.core.sensor.TouchSensor;
import com.ev34j.core.utils.Delay;

//java -cp ev3-lang-java-0.2-SNAPSHOT.jar TouchSensorDemo
public class TouchSensorDemo {

  //Robot Configuration
  private static final TouchSensor touch1 = new TouchSensor(SensorPort.S2,
                                                            ConnnectionType.EV3_ANALOG,
                                                            SensorType.EV3_TOUCH);

  //Configuration
  private static final int HALF_SECOND = 500;

  public static void main(String[] args) {

    final SensorMode sensorMode = touch1.getTouchMode();

    //Robot control loop
    final int iteration_threshold = 5;
    for (int i = 0; i <= iteration_threshold; i++) {

      float[] sample = new float[sensorMode.sampleSize()];
      sensorMode.fetchSample(sample, 0);
      final int touchValue = (int) sample[0];

      System.out.println("Iteration: " + i);
      System.out.println("Battery: " + Battery.getInstance().getVoltage());
      System.out.println("Touch: " + touchValue);
      System.out.println();

      Delay.delayMillis(HALF_SECOND);
    }

  }

}
