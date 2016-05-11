package com.ev34j.mindstorm;

import com.ev34j.mindstorm.motor.LargeMotor;
import com.ev34j.mindstorm.sensor.Ev3TouchSensor;

public class PiStormTest {

  public static void main(String[] args) {
    TestSupport.batteryTest();
    TestSupport.touchSensorTest(new Ev3TouchSensor("BBS1"));
    TestSupport.motorTest(new LargeMotor("BM1"));
  }
}
