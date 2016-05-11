package examples.motors;

import com.ev34j.core.motor.BaseUnregulatedMotor;
import com.ev34j.core.motor.MotorPort;
import com.ev34j.core.utils.Delay;

//java -cp ev3-lang-java-0.2-SNAPSHOT.jar UnregulatedMotorDemo2
public class UnregulatedMotorDemo2 {

  //Robot Definition
  private static final BaseUnregulatedMotor umotor1 = new BaseUnregulatedMotor(MotorPort.D);

  //Configuration
  private final static int motorPower = 50;
  private final static int ONE_SECOND = 1000;

  public static void main(String[] args) {

    //Set power for DC Motors
    umotor1.setPower(motorPower);

    //Testing DC-Motor 1
    umotor1.forward();
    System.out.println(umotor1.isMoving());
    Delay.delayMillis(ONE_SECOND);
    umotor1.stop();
    System.out.println(umotor1.isMoving());
    umotor1.backward();
    System.out.println(umotor1.isMoving());
    Delay.delayMillis(ONE_SECOND);
    umotor1.stop();
    System.out.println(umotor1.isMoving());
    umotor1.forward();
    System.out.println(umotor1.isMoving());
    Delay.delayMillis(ONE_SECOND);
    umotor1.stop();

    System.exit(0);
  }

}
