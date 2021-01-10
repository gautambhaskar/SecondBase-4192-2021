/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class HalfSpeedDrive extends CommandBase {
  /**
   * Creates a new HalfSpeedDrive.
   */
  private final Drivetrain x_Drive;
  private final DoubleSupplier x_forward;
  private final DoubleSupplier x_rotation;

  public HalfSpeedDrive(Drivetrain m_Drive, DoubleSupplier m_forward, DoubleSupplier m_rotation) {
    // Use addRequirements() here to declare subsystem dependencies.
    x_Drive = m_Drive;
    x_forward = m_forward;
    x_rotation = m_rotation;
    addRequirements(m_Drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    x_Drive.move(x_forward.getAsDouble() / 2, x_rotation.getAsDouble() / 2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
