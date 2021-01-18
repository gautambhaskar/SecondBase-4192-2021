/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class TurnSetDegree extends PIDCommand {
  /**
   * Creates a new TurnSetDegree.
   */
  public TurnSetDegree(Drivetrain x_Drive, double degrees) {
    super(
        // The controller that the command will use
        new PIDController(0.08, 0, 0),
        // This should return the measurement
        () -> (x_Drive.getLeftEncoderPosition()-x_Drive.getRightEncoderPosition())/2,//might have to negate this expression
        // This should return the setpoint (can also be a constant)
        () -> degrees * Constants.encoderRotationsPer360DegreeRotation/360,
        // This uses the output
        output -> {
          x_Drive.move(0, output);
          // Use the output here
        });
    x_Drive.recalibrateEncoderPosition();
    addRequirements(x_Drive);
    getController().setTolerance(0.2);
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
  }
}
