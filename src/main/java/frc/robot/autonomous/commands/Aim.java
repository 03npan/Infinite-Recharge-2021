package frc.robot.autonomous.commands;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.LimelightWrapper;
import frc.robot.utils.Motors;
import frc.robot.utils.OI;

/**
 * Aim
 */
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
    public void end(final boolean interrupted) {
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    }

    private NetworkTable getTable() {
        return NetworkTableInstance.getDefault().getTable("limelight");
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    private void turnToTarget() {

        Update_Limelight_Tracking();
        NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
        if (m_LimelightHasValidTarget) {
            System.out.println("Has valid target!");
            System.out.println(m_LimelightDriveCommand);
            System.out.println(m_LimelightSteerCommand);
            System.out.println(LimelightWrapper.isTargetAvalible());
            Motors.drive.arcadeDrive(m_LimelightDriveCommand, m_LimelightSteerCommand);
        } else {
            System.out.println("No target found...");
            System.out.println(m_LimelightDriveCommand);
            System.out.println(m_LimelightSteerCommand);
            System.out.println(LimelightWrapper.isTargetAvalible());
            Motors.drive.arcadeDrive(0.0, 0.0);
        }
    }

    public void Update_Limelight_Tracking() {
        // These numbers must be tuned for your Robot! Be careful!
        final double STEER_K = 0.03; // how hard to turn toward the target
        final double DRIVE_K = 0.26; // how hard to drive fwd toward the target
        final double DESIRED_TARGET_AREA = 13.0; // Area of the target when the robot reaches the wall
        final double MAX_DRIVE = 0.5; // Simple speed limit so we don't drive too fast
        final double MIN_DRIVE = -0.5; //Simple speed limit for backing up

        final double x = LimelightWrapper.getX();
        final double y = LimelightWrapper.getY();
        final double area = LimelightWrapper.getArea();

        if (LimelightWrapper.isTargetAvalible() == 0.0) {
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

        if (drive_cmd < MIN_DRIVE) {
            drive_cmd = MIN_DRIVE;
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

        if(tx > 1.0) {
            steering_adjust = KpAim * heading_error - min_aim_command;
        } else if(tx < 1.0) {
            steering_adjust = KpAim * heading_error + min_aim_command;
        }

        double distance_adjust = KpDistance * distance_error;

        double left_command = 0;
        left_command += steering_adjust + distance_error;
        double right_command = 0;
        right_command -= steering_adjust + distance_adjust;

        Motors.drive.tankDrive(left_command, right_command);
    }

}