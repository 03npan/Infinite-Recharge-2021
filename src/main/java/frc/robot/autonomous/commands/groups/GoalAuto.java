package frc.robot.autonomous.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autonomous.commands.Aim;
import frc.robot.autonomous.commands.DriveStraight;
import frc.robot.autonomous.commands.RotateToAngle;
import frc.robot.autonomous.subsystems.BottomFeedSubsystem;
import frc.robot.autonomous.subsystems.ShooterSubsystem;
import frc.robot.autonomous.subsystems.TopFeedSubsystem;

/**
 * GoalAuto
 */
public class GoalAuto extends SequentialCommandGroup {


    public GoalAuto(ShooterSubsystem shoot, TopFeedSubsystem topFeed, BottomFeedSubsystem bottomFeed) {
        addCommands(
            new DriveStraight(0.5),
            new RotateToAngle(90),
            new Aim(),
            new Shoot());
    }
    
}