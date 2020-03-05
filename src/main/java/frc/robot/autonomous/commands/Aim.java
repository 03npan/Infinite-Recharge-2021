package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.autonomous.subsystems.AimSubsystem;

/**
 * Aim
 */
public class Aim extends CommandBase {

    private final AimSubsystem m_aim;

    public Aim(AimSubsystem m_aim) {
        this.m_aim = m_aim;
        addRequirements(m_aim);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_aim.turnToTarget();
    }

    @Override
    public void end(final boolean interrupted) {
        m_aim.stopAiming();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}