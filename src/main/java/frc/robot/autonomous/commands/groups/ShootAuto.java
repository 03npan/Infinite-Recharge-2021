/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.autonomous.commands.BottomFeed;
import frc.robot.autonomous.commands.Shooter;
import frc.robot.autonomous.commands.TopFeed;
import frc.robot.autonomous.subsystems.BottomFeedSubsystem;
import frc.robot.autonomous.subsystems.ShooterSubsystem;
import frc.robot.autonomous.subsystems.TopFeedSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ShootAuto extends SequentialCommandGroup {
  /**
   * Creates a new AutoShoot.
   */
  public ShootAuto(ShooterSubsystem m_shooter, TopFeedSubsystem m_topFeed, BottomFeedSubsystem m_bottomFeed) {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new Shooter(m_shooter), new TopFeed(m_topFeed), new BottomFeed(m_bottomFeed));
  }
}
