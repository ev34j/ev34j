package com.ev34j.mindstorm;

import com.ev34j.mindstorm.motor.SteeringMotors;
import com.ev34j.mindstorm.time.Wait;

public class MotorTest {

  public static void main(String[] args) {

    SteeringMotors steering = new SteeringMotors("A", "B");
    int prePos1 = steering.getPosition1();
    int prePos2 = steering.getPosition2();
    steering.on(0, 20);
    int power1 = steering.getPower1();
    int power2 = steering.getPower2();
    Wait.forSecs(1);
    int postPos1 = steering.getPosition1();
    int postPos2 = steering.getPosition2();
    steering.on(0, -20);
    int power3 = steering.getPower1();
    int power4 = steering.getPower2();
    Wait.forSecs(1);
    int postPos3 = steering.getPosition1();
    int postPos4 = steering.getPosition2();
    steering.off();

    System.out.println();
  }
}
