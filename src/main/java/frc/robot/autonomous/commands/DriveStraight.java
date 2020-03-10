package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.autonomous.subsystems.DriveSubsystem;
import frc.robot.utils.Motors;

/**
 * DriveStraight
 */
public class DriveStraight extends CommandBase {
    /**
     * Creates a new DriveStraight.
     */

    // private Timer timer = new Timer();;

    private double speed;

    private DriveSubsystem m_driveTrain;

    public DriveStraight(DriveSubsystem driveTrain, double speed) {
        this.speed = speed;
        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(driveTrain);
        m_driveTrain = driveTrain;
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        m_driveTrain.arcadeDrive(-speed, 0);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
<<<<<<< HEAD
        m_driveTrain.stopDriveTrain();
=======
        m_driveTrain.stopDriveTrain(); // Test
>>>>>>> c39986153668a114f5705b7fc926246a8d9c20f0
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}