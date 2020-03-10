package frc.robot.autonomous.commands.groups.teleop;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autonomous.commands.DriveBack;
import frc.robot.autonomous.subsystems.DriveSubsystem;

/**
 * GoalAuto
 */
public class DriveBackAuto extends SequentialCommandGroup {

    public DriveBackAuto(DriveSubsystem driveTrain) {
        addCommands(new DriveBack(driveTrain, 0.6).withTimeout(0.75), 
        new DriveBack(driveTrain, 0).withTimeout(0.1), new Shoot().withTimeout(3));
        // Drive back at 60% for 0.75 seconds, then stop driving back, then run the Shoot command group for 3 seconds
    }

}