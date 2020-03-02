package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ShooterSubsystem;

public class Shoot extends CommandBase {
    private final ShooterSubsystem m_shooter;

    public Shoot(ShooterSubsystem shoot) {
        m_shooter = shoot;
        addRequirements(m_shooter);
    }

    @Override
    public void initialize() {
        m_shooter.runShooter();
    }

    @Override
    public void execute() {
        //m_shooter.runShooter(); TRY HERE IF IT DOESN'T WORK IN INITIALIZE
    }

    @Override
    public void end(boolean interrupted) {
        m_shooter.stopShooter();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
