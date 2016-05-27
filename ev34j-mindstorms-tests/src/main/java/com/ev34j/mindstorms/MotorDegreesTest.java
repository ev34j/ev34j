package com.ev34j.mindstorms;

import com.ev34j.mindstorms.motor.LargeMotor;
import com.ev34j.mindstorms.motor.SteeringMotors;
import com.ev34j.mindstorms.motor.TankMotors;

public class MotorDegreesTest {

  public static void main(String[] args) {

    SteeringMotors steering = new SteeringMotors("A", "B");

    steering.onForDegrees(0, 25, 720);
    steering.waitUntilStopped();
    steering.onForDegrees(0, -25, 720);
    steering.waitUntilStopped();

    steering.onForDegrees(50, 25, 720);
    steering.waitUntilStopped();
    steering.onForDegrees(50, -25, 720);
    steering.waitUntilStopped();

    steering.onForDegrees(-50, 25, 720);
    steering.waitUntilStopped();
    steering.onForDegrees(-50, -25, 720);
    steering.waitUntilStopped();

    TankMotors tank = new TankMotors("A", "B");

    tank.onForDegrees(25, 25, 720);
    tank.waitUntilStopped();
    tank.onForDegrees(-25, -25, 720);
    tank.waitUntilStopped();
    tank.onForDegrees(25, 12, 720);
    tank.waitUntilStopped();
    tank.onForDegrees(-25, -12, 720);
    tank.waitUntilStopped();
    tank.onForDegrees(12, 25, 720);
    tank.waitUntilStopped();
    tank.onForDegrees(-12, -25, 720);
    tank.waitUntilStopped();

    LargeMotor lmA = new LargeMotor("A");

    lmA.onForDegrees(25, 360);
    lmA.waitUntilStopped();
    lmA.onForDegrees(-25, 360);
    lmA.waitUntilStopped();

    LargeMotor lmB = new LargeMotor("B");

    lmB.onForDegrees(25, 360);
    lmB.waitUntilStopped();
    lmB.onForDegrees(-25, 360);
    lmB.waitUntilStopped();
  }
}
