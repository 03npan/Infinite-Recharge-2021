/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous.commands.groups.auto;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.autonomous.commands.Shooter;
import frc.robot.autonomous.subsystems.DriveSubsystem;
import frc.robot.autonomous.subsystems.ShooterSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class GoalAuto2 extends ParallelCommandGroup {
  /**
   * Creates a new GoalAuto2.
   */
  public GoalAuto2(ShooterSubsystem m_shooter, DriveSubsystem driveTrain) {
    addCommands(new Shooter(m_shooter, 0.51).withTimeout(2.6), new GoalAuto(m_shooter, driveTrain));
    // Turn the shooter wheel on at 51% while simultaneously running the GoalAuto command group  
  }
}
