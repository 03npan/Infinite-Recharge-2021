package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
    // Left side motors
    private final SpeedControllerGroup left =
        new SpeedControllerGroup(new WPI_VictorSPX(DriveConstants.VICTOR_LEFT_1),
                                 new WPI_VictorSPX(DriveConstants.VICTOR_LEFT_2));

    // Right side motors
    private final SpeedControllerGroup right =
        new SpeedControllerGroup(new WPI_VictorSPX(DriveConstants.VICTOR_RIGHT_1),
                                 new WPI_VictorSPX(DriveConstants.VICTOR_RIGHT_2));

    // Robot's drive
    private final DifferentialDrive drive = new DifferentialDrive(left, right);

    // Drive subsystem
    public DriveSubsystem() {
        drive.setSafetyEnabled(false); //not sure where this should go
    }

    // Drives the robot - NEED TO ADD IN
    public void driveRobot(double fwd, double rot) {

    }

    // Sets max output of drive.
    public void setMaxOutput(double maxOutput) {
        drive.setMaxOutput(maxOutput);
    }
}