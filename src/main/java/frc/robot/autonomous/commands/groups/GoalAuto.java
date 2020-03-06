package frc.robot.autonomous.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autonomous.commands.Aim;
import frc.robot.autonomous.commands.BottomFeed;
import frc.robot.autonomous.commands.RotateToAngle;
import frc.robot.autonomous.subsystems.AimSubsystem;

/**
 * GoalAuto
 */
public class GoalAuto extends SequentialCommandGroup {


    public GoalAuto(AimSubsystem m_aim) {
        addCommands(
            new RotateToAngle(90),
            new Aim(m_aim),
            new Shoot());
    }
    
}