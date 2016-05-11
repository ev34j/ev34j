package examples.motors;

import com.ev34j.core.battery.Battery;
import com.ev34j.core.motor.MediumRegulatedMotor;
import com.ev34j.core.motor.MotorPort;
import com.ev34j.core.utils.Delay;

//java -cp ev3-lang-java-0.2-SNAPSHOT.jar RegulatedMotorDemo
public class RegulatedMotorDemo {

  public static void main(String[] args) {

    final MediumRegulatedMotor mA = new MediumRegulatedMotor(MotorPort.C);
    mA.setSpeed(500);

    int ONE_SECOND = 1000;

    mA.forward();
    System.out.println(mA.isMoving());
    Delay.delayMillis(ONE_SECOND);
    mA.stop();
    System.out.println(mA.isMoving());
    mA.backward();
    System.out.println(mA.isMoving());
    Delay.delayMillis(ONE_SECOND);
    mA.stop();
    System.out.println(mA.isMoving());
    mA.forward();
    System.out.println(mA.isMoving());
    Delay.delayMillis(ONE_SECOND);
    mA.stop();
    System.out.println(Battery.getInstance().getVoltage());

    mA.close();
    System.exit(0);
  }
}
