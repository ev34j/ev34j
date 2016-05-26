# Java bindings for ev3dev

[![Build Status](https://travis-ci.org/ev34j/ev34j.svg?branch=master)](https://travis-ci.org/ev34j/ev34j)
[![Release](https://jitpack.io/v/ev34j/ev34j.svg)](https://jitpack.io/#ev34j/ev34j)

A Java library for [ev3dev](http://www.ev3dev.org) devices.

## Introduction

The [Lego Mindstorm visual editor](http://www.lego.com/en-us/mindstorms/downloads/download-software)
is a great first programming environment. However, as things get more complex,
visual programming can get cumbersome and you want to  use code rather than blocks.
The [ev34j-mindstorm library](https://github.com/ev34j/ev34j) is meant to bridge the transition to code
by providing simple Java objects that map directly to the Mindstorm blocks in
the visual editor.

## Example Code

```java
import com.ev34j.mindstorm.motor.SteeringMotors;

public class SimpleSteeringMotors {

  public static void main(String[] args) {

    System.out.println("Trying out SteeringMotors");
    SteeringMotors motors = new SteeringMotors("A", "B");

    // Go forward at a 45 degree angle
    motors.onForRotations(2, 50, 25);
    motors.waitUntilStopped();

    // Go back at a 45 degree angle
    motors.onForRotations(2, 50, -25);
    motors.waitUntilStopped();
  }
}
```

## Compatibility

The ev34j-mindstorm library works with these devices:

* [Lego Mindstorm - EV3](http://www.lego.com/en-us/mindstorms/about-ev3)
* [BrickPi](http://www.dexterindustries.com/brickpi/)
* [PiStorm](http://www.mindsensors.com/teaching-stem-with-robotics/13-pistorms-base-kit-raspberry-pi-brain-for-lego-robot)

## Usage

The [ev34j-mindstorm-tutorial](https://github.com/ev34j/ev34j-mindstorm-tutorial) repo
describes how to setup your environment and execute ev34j-based programs.

## The ev34j-mindstorm classes

The ev34j-mindstorm classes are outlined
[here](https://github.com/ev34j/ev34j-mindstorm-tutorial/wiki/Ev34j-Mindstorm-Object-Summary)
and the javadocs are [here](http://docs.ev34j.com).

## Acknowledgments

Sincere thanks to:

* [@dlech](https://github.com/dlech) and [@rhempel](https://github.com/rhempel) for their wonderful [ev3dev.org](http://www.ev3dev.org) effort.

* [@jabrena](https://github.com/jabrena) for his work to provide a LeJOS-like Java API at [ev3dev-lang-java](https://github.com/ev3dev-lang-java/ev3dev-lang-java).