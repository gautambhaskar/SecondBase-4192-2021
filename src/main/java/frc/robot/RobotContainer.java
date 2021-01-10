/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Auton1;
import frc.robot.commands.BasicDrive;
import frc.robot.commands.DriveBack;
import frc.robot.commands.DriveForward;
import frc.robot.commands.HalfSpeedDrive;
import frc.robot.commands.TurnRight;
import frc.robot.subsystems.Drivetrain;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  // Subsystems
  private final Drivetrain m_drive = new Drivetrain();

  // Controllers
  private XboxController dController = new XboxController(Constants.driverController);

  // Commands
  private final BasicDrive basicDrive;
  private final DriveBack back = new DriveBack(m_drive, 0.3);
  private final TurnRight trnDrive = new TurnRight(m_drive, 0.5);
  private final DriveForward forwardDrive = new DriveForward(m_drive, 0.8);
  private final Auton1 BobsAuton = new Auton1(m_drive);
  private final HalfSpeedDrive JeffsDrive;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    basicDrive = new BasicDrive(m_drive, () -> dController.getY(Hand.kLeft), () -> dController.getX(Hand.kRight));
    m_drive.setDefaultCommand(basicDrive);

    JeffsDrive = new HalfSpeedDrive(m_drive, () -> dController.getY(Hand.kLeft), () -> dController.getX(Hand.kRight));
    // Configure the button bindings
    configureButtonBindings();

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private JoystickButton driverA = new JoystickButton(dController, Constants.aButton);
  private JoystickButton turnButton = new JoystickButton(dController, Constants.rightBumper);
  private JoystickButton forwX = new JoystickButton(dController, Constants.xButton);
  private JoystickButton halfY = new JoystickButton(dController, Constants.yButton);

  private void configureButtonBindings() {
    driverA.whenHeld(back);
    turnButton.whenHeld(trnDrive);
    forwX.whenHeld(forwardDrive);
    halfY.toggleWhenPressed(JeffsDrive);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous

    return BobsAuton;
  }
}
