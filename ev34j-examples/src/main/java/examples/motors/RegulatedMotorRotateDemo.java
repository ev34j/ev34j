package examples.motors;

import com.ev34j.core.battery.Battery;
import com.ev34j.core.motor.LargeRegulatedMotor;
import com.ev34j.core.motor.MotorPort;
import com.ev34j.core.sound.Sound;
import com.ev34j.core.utils.Delay;

//java -cp ev3-lang-java-0.2-SNAPSHOT.jar RegulatedMotorRotateDemo
public class RegulatedMotorRotateDemo {

  public static void main(String[] args) {

    Sound sound = Sound.getInstance();

    final int degreesToTurn = 90;

    final LargeRegulatedMotor mA = new LargeRegulatedMotor(MotorPort.A);
    mA.setSpeed(100);

    sound.beep(500);
    System.out.println(mA.getTachoCount());
    mA.rotate(degreesToTurn);
    sound.beep(500);
    Delay.millis(1000);
    System.out.println(mA.getTachoCount());
    mA.rotate(degreesToTurn);
    sound.beep(500);
    Delay.millis(1000);
    System.out.println(mA.getTachoCount());
    mA.rotate(degreesToTurn);
    sound.beep(500);
    Delay.millis(1000);
    System.out.println(mA.getTachoCount());
    mA.rotate(degreesToTurn);
    sound.beep(500);
    Delay.millis(1000);
    System.out.println(mA.getTachoCount());
    System.out.println(Battery.getInstance().getVoltage());

    mA.close();
    System.exit(0);
  }
}
