package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.FeederSubsystem;

public class BottomFeed extends CommandBase {
    private final FeederSubsystem m_feeder;

    public BottomFeed(FeederSubsystem feeder) {
        m_feeder = feeder;
        addRequirements(m_feeder);
    }

    @Override
    public void initialize() {
        m_feeder.runBottomFeeder();
    }

    @Override
    public void execute() {
        //m_feeder.runFeeder(); TRY HERE IF IT DOESN'T WORK IN INITIALIZE
    }

    @Override
    public void end(boolean interrupted) {
        m_feeder.stopBottomFeeder();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
