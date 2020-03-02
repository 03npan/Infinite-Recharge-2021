/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous.commands;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Motors;
import frc.robot.utils.NavX;

public class RotateToAngle extends CommandBase {
  /**
   * Creates a new RotateToAngle.
   */

  private float kTargetAngleDegrees;
  private DifferentialDrive drive = Motors.drive;
  private PIDController turnController;
  private AHRS ahrs;

  private XboxController driverController;

  private NavX navX;

  static final double kToleranceDegrees = 2.0f;
  double rotateToAngleRate;

  public RotateToAngle(float kTargetAngleDegrees, PIDController turnController, AHRS ahrs, NavX navX,
      XboxController driverController) {
    this.kTargetAngleDegrees = kTargetAngleDegrees;
    this.turnController = turnController;
    this.ahrs = ahrs;
    this.navX = navX;
    rotateToAngleRate = navX.getRotateToAngleRate();
    this.driverController = driverController;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
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

  /*
   * "Zero" the yaw (whatever direction the sensor is pointing now will become the
   * new "Zero" degrees. ahrs.zeroYaw();
   */

  /*
   * While this button is held down, the robot is in "drive straight" mode.
   * Whatever direction the robot was heading when "drive straight" mode was
   * entered will be maintained. The average speed of both joysticks is the
   * magnitude of motion. if (!turnController.isEnabled()) { // Acquire current
   * yaw angle, using this as the target angle.
   * turnController.setSetpoint(ahrs.getYaw()); rotateToAngleRate = 0; // This
   * value will be updated in the pidWrite() method. turnController.enable(); }
   * double magnitude = (driverController.getRawAxis(2) +
   * driverController.getRawAxis(3)) / 2; double leftStickValue = magnitude +
   * rotateToAngleRate; double rightStickValue = magnitude - rotateToAngleRate;
   * drive.tankDrive(leftStickValue, rightStickValue);
   */

  private void rotateToAngle() {
    System.out.println("1");
    if (!turnController.isEnabled()) {
      System.out.println("2");
      turnController.setSetpoint(kTargetAngleDegrees);
      System.out.println("3");
      rotateToAngleRate = 0; // This value will be updated in the pidWrite() method.
      turnController.enable();
      System.out.println("4");
    }
    System.out.println("5");
    double leftStickValue = rotateToAngleRate;
    double rightStickValue = rotateToAngleRate;
    System.out.println("6");
    drive.tankDrive(leftStickValue, rightStickValue);
    System.out.println("7. Current Rotate Angle Rate: " + rotateToAngleRate);
  }
}
