package frc.robot.autonomous.commands.groups.auto;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autonomous.commands.DriveStraight;
import frc.robot.autonomous.commands.groups.teleop.Shoot;
import frc.robot.autonomous.subsystems.DriveSubsystem;
import frc.robot.autonomous.subsystems.ShooterSubsystem;

/**
 * GoalAuto
 */
public class GoalAuto extends SequentialCommandGroup {

    public GoalAuto(ShooterSubsystem m_shooter, DriveSubsystem driveTrain) {
        addCommands(new DriveStraight(driveTrain, 0.5).withTimeout(2.6), 
        new DriveStraight(driveTrain, 0).withTimeout(0.1), new Shoot());
        // Drive straight at 50% for 2.6 seconds, then stop driving straight, then run the Shoot command group
    }

}