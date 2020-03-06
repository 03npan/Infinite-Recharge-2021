package frc.robot.autonomous.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autonomous.commands.Aim;
import frc.robot.autonomous.commands.DriveStraight;
import frc.robot.autonomous.commands.RotateToAngle;
import frc.robot.autonomous.subsystems.AimSubsystem;
import frc.robot.autonomous.subsystems.BottomFeedSubsystem;
import frc.robot.autonomous.subsystems.DriveStraightSubsystem;
import frc.robot.autonomous.subsystems.ShooterSubsystem;
import frc.robot.autonomous.subsystems.TopFeedSubsystem;

/**
 * GoalAuto
 */
public class GoalAuto extends SequentialCommandGroup {

    public GoalAuto(DriveStraightSubsystem m_straight, AimSubsystem m_aim, ShooterSubsystem m_shooter,
            TopFeedSubsystem m_topFeed, BottomFeedSubsystem m_bottomFeed) {
        addCommands(new DriveStraight(m_straight), new RotateToAngle(90), new Aim(m_aim),
                new ShootAuto(m_shooter, m_topFeed, m_bottomFeed));
    }

}