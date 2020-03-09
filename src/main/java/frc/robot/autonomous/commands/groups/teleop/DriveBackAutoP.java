package frc.robot.autonomous.commands.groups.teleop;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.autonomous.commands.DriveBack;
import frc.robot.autonomous.subsystems.DriveSubsystem;

/**
 * GoalAuto
 */
public class DriveBackAutoP extends ParallelCommandGroup {

    public DriveBackAutoP(DriveSubsystem driveTrain) {
        addCommands(new DriveBack(driveTrain, 0.6).withTimeout(1), new Shoot().withTimeout(3));
        // Drive back at 60% for 1 second while also running the Shoot command group for 3 seconds
    }

}