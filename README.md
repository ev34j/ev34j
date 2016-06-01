# Java bindings for ev3dev

[![Build Status](https://travis-ci.org/ev34j/ev34j.svg?branch=master)](https://travis-ci.org/ev34j/ev34j)
[![Release](https://jitpack.io/v/ev34j/ev34j.svg)](https://jitpack.io/#ev34j/ev34j)

A Java library for [ev3dev](http://www.ev3dev.org) devices.

## Introduction

The [Lego Mindstorms visual editor](http://www.lego.com/en-us/mindstorms/downloads/download-software)
is a great first programming environment. However, as things get more complex,
visual programming can get cumbersome and you want to  use code rather than blocks.
The [ev34j-mindstorms library](https://github.com/ev34j/ev34j) is meant to bridge the transition to code
by providing simple Java objects that map directly to the Mindstorms blocks in
the visual editor.

## Example Code

```java
import com.ev34j.mindstorms.motor.SteeringMotors;

public class SimpleSteeringMotors {

  public static void main(String[] args) {

    System.out.println("Trying out SteeringMotors");
    SteeringMotors motors = new SteeringMotors("A", "B");

    // Go forward 2 rotations at 25% power
    motors.onForRotations(0, 25, 2);
    motors.waitUntilStopped();

    // Go back 2 rotations at 25% power
    motors.onForRotations(0, -25, 2);
    motors.waitUntilStopped();
  }
}
```

## Compatibility

The ev34j-mindstorms library works with these devices:

* [Lego Mindstorms - EV3](http://www.lego.com/en-us/mindstorms/about-ev3)
* [BrickPi](http://www.dexterindustries.com/brickpi/)
* [PiStorms](http://www.mindsensors.com/teaching-stem-with-robotics/13-pistorms-base-kit-raspberry-pi-brain-for-lego-robot)

## Usage

The [ev34j-mindstorms-tutorial](https://github.com/ev34j/ev34j-mindstorms-tutorial) repo
describes how to setup your environment and execute ev34j-based programs.

## The ev34j-mindstorms classes

The ev34j-mindstorms classes are outlined
[here](https://github.com/ev34j/ev34j-mindstorm-tutorial/wiki/Ev34j-Mindstorms-Object-Summary)
and the javadocs are [here](http://www.ev34j.com/javadocs/).

## Acknowledgments

Sincere thanks to:

* [@dlech](https://github.com/dlech) and [@rhempel](https://github.com/rhempel) for their excellent [ev3dev.org](http://www.ev3dev.org) effort.

* [@jabrena](https://github.com/jabrena) for his work to provide a LeJOS-like Java API at [ev3dev-lang-java](https://github.com/ev3dev-lang-java/ev3dev-lang-java).