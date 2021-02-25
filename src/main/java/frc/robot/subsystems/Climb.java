// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climb extends SubsystemBase {
  private final DoubleSolenoid climbSolenoid = new DoubleSolenoid(16, 0, 1);
  public final DoubleSolenoid.Value lowerRobot = DoubleSolenoid.Value.kForward;
  public final DoubleSolenoid.Value liftRobot = DoubleSolenoid.Value.kReverse;

  /** Creates a new Climb. */

  public Climb() {
    lowerRobot();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void liftRobot() {
    climbSolenoid.set(liftRobot);
  }

  public void lowerRobot() {
    climbSolenoid.set(lowerRobot);
  }

}
