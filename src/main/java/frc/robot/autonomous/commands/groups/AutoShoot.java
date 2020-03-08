package frc.robot.autonomous.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autonomous.commands.DriveBack;
import frc.robot.autonomous.subsystems.DriveSubsystem;
import frc.robot.autonomous.subsystems.ShooterSubsystem;

/**
 * LineUpShot
 */
public class AutoShoot extends SequentialCommandGroup {

    public AutoShoot(ShooterSubsystem m_shooter, DriveSubsystem driveTrain) {
        addCommands(new DriveBack(driveTrain, 0.7).withTimeout(1.5), 
        new DriveBack(driveTrain, 0).withTimeout(0.1), new Shoot().withTimeout(4));
    }

}