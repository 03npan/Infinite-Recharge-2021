package frc.robot.autonomous.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autonomous.commands.Aim;
import frc.robot.autonomous.commands.DriveStraight;
import frc.robot.autonomous.commands.RotateToAngle;
import frc.robot.autonomous.subsystems.AimSubsystem;

/**
 * GoalAuto
 */
public class GoalAuto extends SequentialCommandGroup {


    public GoalAuto(AimSubsystem m_aim) {
        addCommands(
            new DriveStraight(0.5),
            new RotateToAngle(90),
            new Aim(m_aim),
            new Shoot());
    }
    
}