package examples.motors;

import com.ev34j.core.battery.Battery;
import com.ev34j.core.motor.LargeRegulatedMotor;
import com.ev34j.core.motor.MotorPort;
import com.ev34j.core.sound.Sound;
import com.ev34j.core.utils.Delay;

//java -cp ev3-lang-java-0.2-SNAPSHOT.jar RegulatedMotorRotateToDemo
public class RegulatedMotorRotateToDemo {

  public static void main(String[] args) {

    Sound sound = Sound.getInstance();

    final int degreesToTurn = 90;
    int currentDegrees = 90;

    LargeRegulatedMotor mA = new LargeRegulatedMotor(MotorPort.A);
    mA.setSpeed(100);

    System.out.println(mA.getTachoCount());
    currentDegrees += degreesToTurn;
    System.out.println(currentDegrees);
    mA.rotateTo(currentDegrees);
    sound.beep();
    Delay.delayMillis(1000);
    System.out.println(mA.getTachoCount());
    currentDegrees += degreesToTurn;
    System.out.println(currentDegrees);
    mA.rotateTo(currentDegrees);
    sound.beep();
    Delay.delayMillis(1000);
        
        /*
        System.out.println(mA.getTachoCount());
        currentDegrees += degreesToTurn;
        System.out.println(currentDegrees);
        mA.rotateTo(currentDegrees);
        Sound.beep();
        Delay.msDelay(1000);
        System.out.println(mA.getTachoCount());
        currentDegrees += degreesToTurn;
        System.out.println(currentDegrees);
        mA.rotateTo(currentDegrees);
        Sound.beep();
        System.out.println(mA.getTachoCount());
        currentDegrees += degreesToTurn;
        System.out.println(currentDegrees);
        mA.rotateTo(currentDegrees);
        Sound.beep();
        System.out.println(mA.getTachoCount());
        */
    System.out.println(Battery.getInstance().getVoltage());

    mA.close();
    System.exit(0);
  }
}
