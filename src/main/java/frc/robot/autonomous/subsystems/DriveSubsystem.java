package frc.robot.autonomous.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Motors;

public class DriveSubsystem extends SubsystemBase {

    // Robot's drive
    private final DifferentialDrive drive = Motors.drive;

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