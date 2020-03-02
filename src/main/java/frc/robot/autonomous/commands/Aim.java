/*

WORK IN PROGRESS, WILL BE CONVERTED SOON

package frc.robot.autonomous.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.LimelightWrapper;

public class Aim extends CommandBase {

    private boolean m_LimelightHasValidTarget = false;
    private double m_LimelightDriveCommand = 0.0;
    private double m_LimelightSteerCommand = 0.0;

    public Aim() {
        LimelightWrapper.ledMode(true);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        turnToTarget();
    }

    @Override
    public void end(boolean interrupted) {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    private void turnToTarget() {

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

        double x = LimelightWrapper.getX();
        double y = LimelightWrapper.getY();
        double area = LimelightWrapper.getArea();

        if (!LimelightWrapper.isTargetAvalible()) {
            m_LimelightHasValidTarget = false;
            m_LimelightDriveCommand = 0.0;
            m_LimelightSteerCommand = 0.0;
            return;
        }

        m_LimelightHasValidTarget = true;

        // Start with proportional steering
        double steer_cmd = x * STEER_K;
        m_LimelightSteerCommand = steer_cmd;

        // try to drive forward until the target area reaches our desired area
        double drive_cmd = (DESIRED_TARGET_AREA - area) * DRIVE_K;

        // don't let the robot drive too fast into the goal
        if (drive_cmd > MAX_DRIVE) {
            drive_cmd = MAX_DRIVE;
        }
        m_LimelightDriveCommand = drive_cmd;
    }

}
*/