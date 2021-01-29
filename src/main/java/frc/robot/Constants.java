/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
    // Controllers
    public static int driverController = 0;

    // Buttons
    public static int aButton = 1;
    public static int bButton = 2;
    public static int xButton = 3;
    public static int yButton = 4;
    public static int leftBumper = 5;
    public static int rightBumper = 6;
    public static int backButton = 7;
    public static int startButton = 8;
    public static int leftStickPressDown = 9;
    public static int rightStickPressDown = 10;

    // Motors
    public static int leftLead = 1;
    public static int leftFollower1 = 2;
    public static int leftFollower2 = 3;
    public static int rightLead = 5;
    public static int rightFollower1 = 6;
    public static int rightFollower2 = 7;

    public static double encoderRotationsPer360DegreeRotation = 40;

    public static class drivePID {
        public static double kP = 0.06;
        public static double kI = 0;
        public static double kD = 0;
        public static double outputMax = 0.5;
    }
    public static double maxRotationSpeed = 0.4;
}
