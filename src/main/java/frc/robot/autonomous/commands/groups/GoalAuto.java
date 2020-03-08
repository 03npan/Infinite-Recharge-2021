package frc.robot.autonomous.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autonomous.commands.DriveStraight;
import frc.robot.autonomous.subsystems.DriveSubsystem;
import frc.robot.autonomous.subsystems.ShooterSubsystem;

/**
 * GoalAuto
 */
public class GoalAuto extends SequentialCommandGroup {

    public GoalAuto(ShooterSubsystem m_shooter, DriveSubsystem driveTrain) {
        addCommands(new DriveStraight(driveTrain, 0.5).withTimeout(4), 
        new DriveStraight(driveTrain, 0).withTimeout(0.1), new Shoot());
    }

}