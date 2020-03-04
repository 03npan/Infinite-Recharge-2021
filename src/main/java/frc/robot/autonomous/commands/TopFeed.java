package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FeederSubsystem;

public class TopFeed extends CommandBase {
    private final FeederSubsystem m_feeder;

    public TopFeed(FeederSubsystem feeder) {
        m_feeder = feeder;
        addRequirements(m_feeder);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_feeder.runTopFeeder();
    }

    @Override
    public void end(boolean interrupted) {
        m_feeder.stopTopFeeder();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}
