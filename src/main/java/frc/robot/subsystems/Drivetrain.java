/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  private final CANSparkMax leftLead = new CANSparkMax(Constants.leftLead, MotorType.kBrushless);
  private final CANSparkMax rightLead = new CANSparkMax(Constants.rightLead, MotorType.kBrushless);
  private final CANSparkMax leftFollower1 = new CANSparkMax(Constants.leftFollower1, MotorType.kBrushless);
  private final CANSparkMax leftFollower2 = new CANSparkMax(Constants.leftFollower2, MotorType.kBrushless);
  private final CANSparkMax rightFollower1 = new CANSparkMax(Constants.rightFollower1, MotorType.kBrushless);
  private final CANSparkMax rightFollower2 = new CANSparkMax(Constants.rightFollower2, MotorType.kBrushless);
  
  private final SpeedControllerGroup m_leftMotors = new SpeedControllerGroup(leftLead, leftFollower1, leftFollower2);
  private final SpeedControllerGroup m_rightMotors = new SpeedControllerGroup(rightLead, rightFollower1, rightFollower2);
  
  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  public Drivetrain() {

    
  
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
  public void move(double forward, double turn) {
    m_drive.arcadeDrive(forward, turn);
  }

}
