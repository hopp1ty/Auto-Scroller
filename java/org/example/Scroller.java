package org.example;

import java.awt.AWTException;
import java.awt.Robot;

public class Scroller {

    public void scroll() {
        try {
            Robot robot = new Robot();

            // Scroll down a specified number of "notches" (mouse wheel rotations)
            // A positive value scrolls down, negative scrolls up.
            int scrollAmount = 1000;
            robot.mouseWheel(scrollAmount);

        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
