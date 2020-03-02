/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import frc.robot.autonomous.subsystems.BottomFeedSubsystem;
import frc.robot.autonomous.subsystems.ShooterSubsystem;
import frc.robot.autonomous.subsystems.TopFeedSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class Shoot extends ParallelDeadlineGroup {
  /**
   * Creates a new Shoot.
   */
  public Shoot() {
    // Add your commands in the super() call.  Add the deadline first.
    super(
        new InstantCommand(),
        new Shooter(new ShooterSubsystem()),
        new TopFeed(new TopFeedSubsystem()),
        new BottomFeed(new BottomFeedSubsystem())
    );
  }
}
