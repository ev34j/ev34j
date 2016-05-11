package ev3;

import com.ev34j.core.battery.Battery;
import com.ev34j.core.motor.LargeRegulatedMotor;
import com.ev34j.core.motor.MotorPort;
import com.ev34j.core.sensor.InfraredSensor;
import com.ev34j.core.sensor.SensorMode;
import com.ev34j.core.sensor.SensorPort;
import com.ev34j.core.utils.Delay;

//java -cp ev3-lang-java-0.2-SNAPSHOT.jar ev3dev.examples.misc.BumperCar
public class Test {

  //Robot Definition
  private final static LargeRegulatedMotor mA  = new LargeRegulatedMotor(MotorPort.A);
  private final static LargeRegulatedMotor mB  = new LargeRegulatedMotor(MotorPort.B);
  private final static InfraredSensor      ir1 = new InfraredSensor(SensorPort.S1);

  //Configuration
  private final static int motorSpeed = 500;

  public static void main(String[] args) {

    final SensorMode sensorMode = ir1.getDistanceMode();
    int distance = 255;

    final int distance_threshold = 35;

    //Robot control loop
    final int iteration_threshold = 100;
    for (int i = 0; i <= iteration_threshold; i++) {
      forward();

      float[] sample = new float[sensorMode.sampleSize()];
      sensorMode.fetchSample(sample, 0);
      distance = (int) sample[0];
      if (distance <= distance_threshold) {
        backwardWithTurn();
      }

      System.out.println("Iteration: " + i);
      System.out.println("Battery: " + Battery.getInstance().getVoltage());
      System.out.println("Distance: " + distance);
      System.out.println();
    }

    mA.stop();
    mB.stop();
    System.exit(0);
  }

  private static void forward() {
    mA.setSpeed(motorSpeed);
    mB.setSpeed(motorSpeed);
    mA.forward();
    mB.forward();
  }

  private static void backwardWithTurn() {
    mA.backward();
    mB.backward();
    Delay.delayMillis(1000);
    mA.stop();
    mB.stop();
    mA.backward();
    mB.forward();
    Delay.delayMillis(1000);
    mA.stop();
    mB.stop();
  }
}