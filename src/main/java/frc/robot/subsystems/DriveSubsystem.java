package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
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
    public void driveRobot(XboxController driverController) {
        double deadZone = 0.15;
        double dif = Math.signum(driverController.getRawAxis(2) - driverController.getRawAxis(3))
            * ((driverController.getRawAxis(2) - driverController.getRawAxis(3))
                * (driverController.getRawAxis(2) - driverController.getRawAxis(3)));
        if (Math.abs(dif) <= deadZone)
        dif = 0.0;

        double turn = Math.signum(driverController.getRawAxis(0))
            * (driverController.getRawAxis(0) * driverController.getRawAxis(0));
        if (Math.abs(turn) <= deadZone)
        turn = 0.0;

        drive.arcadeDrive(dif * 1, (turn) * 0.6); // 0.8
    }

    // Sets max output of drive.
    public void setMaxOutput(double maxOutput) {
        drive.setMaxOutput(maxOutput);
    }
}