package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ClimbSubsystem;;

public class Climb extends CommandBase {
    private final ClimbSubsystem m_climb;

    public Climb (ClimbSubsystem climb) {
        m_climb = climb;
        addRequirements(m_climb);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_climb.climb();
    }

    @Override
    public void end(boolean interrupted) {
        m_climb.stopClimb();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}