package com.navysu.java.basic.others;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

public class MouseEmulator {
    public static void main(String[] args) {
        try {
            Robot robot = new Robot();

            // Move the mouse to (x, y) coordinates
            robot.mouseMove(500, 500);

            // Perform a left-click
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            // Perform a right-click
            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);

            // Perform a double-click
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

            // Do nothing here
            robot.mousePress(MouseEvent.NOBUTTON);
            robot.mouseRelease(MouseEvent.NOBUTTON);

            // Scroll the mouse wheel
            robot.mouseWheel(5);
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }
}
