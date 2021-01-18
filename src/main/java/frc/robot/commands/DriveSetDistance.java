/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.drivePID;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveSetDistance extends PIDCommand {
  /**
   * Creates a new DriveSetDistance.
   */

  public DriveSetDistance(Drivetrain x_Drive, double distance) {
    super(
        // The controller that the command will use
        new PIDController(drivePID.kP, drivePID.kI, drivePID.kD),
        // This should return the measurement
        () -> x_Drive.getLeftEncoderPosition(),
        // This should return the setpoint (can also be a constant)
        () -> -distance,
        // This uses the output
        output -> {
          // Use the output here
          if (output > drivePID.outputMax) {
            x_Drive.move(drivePID.outputMax, 0);
          } else if (output < -drivePID.outputMax) {
            x_Drive.move(-drivePID.outputMax, 0);
          } else {
            x_Drive.move(output, 0);
          }
        });
    x_Drive.recalibrateEncoderPosition();
    addRequirements(x_Drive);
    getController().setTolerance(5);
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
