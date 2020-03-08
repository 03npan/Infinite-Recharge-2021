package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.autonomous.subsystems.DriveSubsystem;
import frc.robot.utils.Motors;

/**
 * DriveBack
 */

public class DriveBack extends CommandBase {
    /**
     * Creates a new DriveBack.
     */

    private double speed;

    private DriveSubsystem m_driveTrain;

    public DriveBack(DriveSubsystem driveTrain, double speed) {
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
        m_driveTrain.arcadeDrive(speed, 0);
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

}