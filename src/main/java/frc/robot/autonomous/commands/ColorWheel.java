package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ColorWheelSubsystem;

public class ColorWheel extends CommandBase {
    private final ColorWheelSubsystem m_color_wheel;

    public ColorWheel (ColorWheelSubsystem colorWheel) {
        m_color_wheel = colorWheel;
        addRequirements(m_color_wheel);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        m_color_wheel.turnColorWheel();
    }

    @Override
    public void end(boolean interrupted) {
        m_color_wheel.stopColorWheel();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

}