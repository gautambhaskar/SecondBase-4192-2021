/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class DriveSetDistance extends PIDCommand {
  /**
   * Creates a new DriveSetDistance.
   */
  public DriveSetDistance(Drivetrain drivetrain, double setPos) {
    super(
        // The controller that the command will use
        new PIDController(0.002, 0, 0), //tune once base is ready
        // This should return the measurement
        () -> drivetrain.getEncoderValue(),
        // This should return the setpoint (can also be a constant)
        () -> setPos,
        // This uses the output
        output -> {
          drivetrain.move(output, 0);
          // Use the output here
        });
    
    addRequirements(drivetrain);
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
    getController().setTolerance(0.05);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return getController().atSetpoint();
    
  }
}
