/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
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
  private final SpeedControllerGroup m_rightMotors = new SpeedControllerGroup(rightLead, rightFollower1,
      rightFollower2);

  private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotors, m_rightMotors);

  private double init_position, right_init_position;
  private ShuffleboardTab dataTab;
  private NetworkTableEntry telem_leftEncoder, telem_rightEncoder;

  public Drivetrain() {
    init_position = leftLead.getEncoder().getPosition();
    dataTab = Shuffleboard.getTab("Data Tab");
    telem_leftEncoder = dataTab.add("Drivetrain Left Encoder", 0).getEntry();
    telem_rightEncoder = dataTab.add("Drivetrain Right Encoder", 0).getEntry();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    // Drivetrain Encoder Telemetry Updates
    telem_leftEncoder.setDouble(leftLead.getEncoder().getPosition() - init_position);
    telem_rightEncoder.setDouble(rightLead.getEncoder().getPosition() - init_position);
  }

  public void move(double forward, double turn) {
    m_drive.arcadeDrive(forward, turn);
  }

  public double getLeftEncoderPosition() {
    return leftLead.getEncoder().getPosition() - init_position;
  }

  public double getRightEncoderPosition() {
    return rightLead.getEncoder().getPosition() - right_init_position;
  }

  public void recalibrateEncoderPosition() {
    init_position = leftLead.getEncoder().getPosition();
    right_init_position = rightLead.getEncoder().getPosition();
  }

}
