package frc.robot.autonomous.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autonomous.commands.DriveBack;
import frc.robot.autonomous.subsystems.DriveSubsystem;

/**
 * LineUpShot
 */
public class AutoShoot extends SequentialCommandGroup {

    public AutoShoot(DriveSubsystem driveTrain) {
        addCommands(new DriveBack(driveTrain, 1).withTimeout(0.7), 
        new DriveBack(driveTrain, 0).withTimeout(0.1), new Shoot().withTimeout(4));
    }

}