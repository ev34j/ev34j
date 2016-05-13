package examples.sensors;

import com.ev34j.core.sensor.InfraredSensor;
import com.ev34j.core.sensor.SensorMode;
import com.ev34j.core.sensor.SensorPort;
import com.ev34j.core.utils.Delay;

//java -cp ev3-lang-java-0.2-SNAPSHOT.jar IRSensorDemo
public class IRSensorDemo {

  //Robot Configuration
  private static final InfraredSensor ir1 = new InfraredSensor(SensorPort.S1);

  //Configuration
  private static final int HALF_SECOND = 500;

  public static void main(String[] args) {

    final SensorMode sensorMode = ir1.getDistanceMode();

    //Robot control loop
    final int iteration_threshold = 50;
    for (int i = 0; i <= iteration_threshold; i++) {

      float[] sample = new float[sensorMode.sampleSize()];
      sensorMode.fetchSample(sample, 0);
      final int distanceValue = (int) sample[0];

      System.out.println("Iteration: " + i);
      System.out.println("Distance: " + distanceValue);
      System.out.println();

      Delay.millis(HALF_SECOND);
    }
  }
}
