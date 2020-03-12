/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous.commands.groups;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.autonomous.commands.DriveBack;
import frc.robot.autonomous.commands.groups.teleop.Shoot;
import frc.robot.autonomous.subsystems.DriveSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class AutoShoot2 extends ParallelCommandGroup {
  /**
   * Creates a new AutoShoot2.
   */
  public AutoShoot2(DriveSubsystem driveTrain) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());super();
    super(
      new DriveBack(driveTrain, 1).withTimeout(0.7), 
      new Shoot().withTimeout(4)
    );
  }
}
