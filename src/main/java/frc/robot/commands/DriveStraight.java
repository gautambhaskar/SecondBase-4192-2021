// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.PIDCommand;
import frc.robot.Constants.drivePID;
import frc.robot.subsystems.Drivetrain;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DriveStraight extends PIDCommand {
  /** Creates a new DriveStraight. */
  public DriveStraight(Drivetrain m_drive, DoubleSupplier m_forward) {
    super(
        // The controller that the command will use
        new PIDController(drivePID.turnkP,0,0),
        // This should return the measurement
        () -> m_drive.getGyroValue(),
        // This should return the setpoint (can also be a constant)
        () -> 0,
        // This uses the output
        output -> {
           m_drive.move(m_forward.getAsDouble(), output); 
          // Use the output here
        });
        addRequirements(m_drive);
    // Use addRequirements() here to declare subsystem dependencies.
    // Configure additional PID options by calling `getController` here.
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
