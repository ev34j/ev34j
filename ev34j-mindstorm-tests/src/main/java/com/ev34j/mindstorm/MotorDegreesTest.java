package com.ev34j.mindstorm;

import com.ev34j.mindstorm.motor.LargeMotor;
import com.ev34j.mindstorm.motor.SteeringMotors;
import com.ev34j.mindstorm.motor.TankMotors;

public class MotorDegreesTest {

  public static void main(String[] args) {

    SteeringMotors steering = new SteeringMotors("A", "B");

    steering.onForDegrees(720, 0, 25);
    steering.waitUntilStopped();
    steering.onForDegrees(720, 0, -25);
    steering.waitUntilStopped();

    steering.onForDegrees(720, 50, 25);
    steering.waitUntilStopped();
    steering.onForDegrees(720, 50, -25);
    steering.waitUntilStopped();

    steering.onForDegrees(720, -50, 25);
    steering.waitUntilStopped();
    steering.onForDegrees(720, -50, -25);
    steering.waitUntilStopped();

    TankMotors tank = new TankMotors("A", "B");

    tank.onForDegrees(720, 25, 25);
    tank.waitUntilStopped();
    tank.onForDegrees(720, -25, -25);
    tank.waitUntilStopped();
    tank.onForDegrees(720, 25, 12);
    tank.waitUntilStopped();
    tank.onForDegrees(720, -25, -12);
    tank.waitUntilStopped();
    tank.onForDegrees(720, 12, 25);
    tank.waitUntilStopped();
    tank.onForDegrees(720, -12, -25);
    tank.waitUntilStopped();

    LargeMotor lmA = new LargeMotor("A");

    lmA.onForDegrees(360, 25);
    lmA.waitUntilStopped();
    lmA.onForDegrees(360, -25);
    lmA.waitUntilStopped();

    LargeMotor lmB = new LargeMotor("B");

    lmB.onForDegrees(360, 25);
    lmB.waitUntilStopped();
    lmB.onForDegrees(360, -25);
    lmB.waitUntilStopped();
  }
}
