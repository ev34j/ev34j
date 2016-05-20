package com.ev34j.mindstorm.buttons;

import com.ev34j.core.buttons.ButtonType;

public class Ev3Buttons {

  private Ev3Buttons() { }

  public static ButtonType waitForButtonPress() { return com.ev34j.core.buttons.Ev3Buttons.getInstance().getButtonPress(); }

}
