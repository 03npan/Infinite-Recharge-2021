/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.LimelightWrapper;
import frc.robot.utils.Motors;

public class AimSubsystem extends SubsystemBase {
  /**
   * Creates a new AimSubsystem.
   */

  private boolean m_LimelightHasValidTarget = false;
  private double m_LimelightDriveCommand = 0.0;
  private double m_LimelightSteerCommand = 0.0;

  public AimSubsystem() {
    LimelightWrapper.ledMode(true);
  }

  public void stopAiming() {
    getTable().getEntry("ledMode").setNumber(1);
  }

  private NetworkTable getTable() {
    return NetworkTableInstance.getDefault().getTable("limelight");
  }

  public void turnToTarget() {
    Update_Limelight_Tracking();
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
    if (m_LimelightHasValidTarget) {
      Motors.drive.arcadeDrive(m_LimelightDriveCommand, m_LimelightSteerCommand);
    } else {
      Motors.drive.arcadeDrive(0.0, 0.0);
    }
  }

  public void Update_Limelight_Tracking() {
    // These numbers must be tuned for your Robot! Be careful!
    final double STEER_K = 0.03; // how hard to turn toward the target
    final double DRIVE_K = 0.26; // how hard to drive fwd toward the target
    final double DESIRED_TARGET_AREA = 13.0; // Area of the target when the robot reaches the wall
    final double MAX_DRIVE = 0.5; // Simple speed limit so we don't drive too fast

    final double x = LimelightWrapper.getX();
    final double y = LimelightWrapper.getY();
    final double area = LimelightWrapper.getArea();

    if (!LimelightWrapper.isTargetAvalible()) {
      m_LimelightHasValidTarget = false;
      m_LimelightDriveCommand = 0.0;
      m_LimelightSteerCommand = 0.0;
      return;
    }

    m_LimelightHasValidTarget = true;

    // Start with proportional steering
    final double steer_cmd = x * STEER_K;
    m_LimelightSteerCommand = steer_cmd;

    // try to drive forward until the target area reaches our desired area
    double drive_cmd = (DESIRED_TARGET_AREA - area) * DRIVE_K;

    // don't let the robot drive too fast into the goal
    if (drive_cmd > MAX_DRIVE) {
      drive_cmd = MAX_DRIVE;
    }
    m_LimelightDriveCommand = drive_cmd;
  }

  private void aimAndRange() {
    float KpAim = -0.1f;
    float KpDistance = -0.1f;
    float min_aim_command = 0.0f;

    double tx = LimelightWrapper.getX();
    double ty = LimelightWrapper.getY();

    double heading_error = -tx;
    double distance_error = -ty;
    double steering_adjust = 0.0f;

    if (tx > 1.0) {
      steering_adjust = KpAim * heading_error - min_aim_command;
    } else if (tx < 1.0) {
      steering_adjust = KpAim * heading_error + min_aim_command;
    }

    double distance_adjust = KpDistance * distance_error;

    double left_command = 0;
    left_command += steering_adjust + distance_error;
    double right_command = 0;
    right_command -= steering_adjust + distance_adjust;

    Motors.drive.tankDrive(left_command, right_command);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
