package examples.motors;

import com.ev34j.core.battery.Battery;
import com.ev34j.core.motor.LargeRegulatedMotor;
import com.ev34j.core.motor.MotorPort;
import com.ev34j.core.sound.Sound;
import com.ev34j.core.utils.Delay;

public class LargeMotorTest {

  public static void main(String[] args)
      throws InterruptedException {

    System.out.println("Starting motor on A");
    final LargeRegulatedMotor mA = new LargeRegulatedMotor(MotorPort.A);
    mA.setSpeed(500);
    mA.forward();
    System.out.println(String.format("Large Motor is moving: %s at speed %d", mA.isMoving(), mA.getSpeed()));
    Delay.delayMillis(2000);
    mA.stop();
    System.out.println("Stopped motor");
    System.out.println("Battery: " + Battery.getInstance().getVoltage());
    Sound.getInstance().playTone(1000, 100);

    System.exit(0);
  }

}
