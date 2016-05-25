package com.ev34j.mindstorm.buttons;

import com.ev34j.core.buttons.ev3.ButtonType;
import com.ev34j.core.buttons.ev3.Buttons;

public class Ev3Buttons {

  private Ev3Buttons() { }

  public static ButtonType waitForButtonPress() { return Buttons.getInstance().getButtonPress(); }

}
