package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.autonomous.subsystems.DriveStraightSubsystem;

/**
 * DriveStraight
 */
public class DriveStraight extends CommandBase {
    /**
     * Creates a new DriveStraight.
     */

    private final DriveStraightSubsystem m_straight;

    public DriveStraight(DriveStraightSubsystem m_straight) {
        this.m_straight = m_straight;
        addRequirements(m_straight);
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        m_straight.driveStraight(2, 0.5);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        m_straight.stopDrivingStraight();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}