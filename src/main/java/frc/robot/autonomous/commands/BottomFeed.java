/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.autonomous.subsystems.BottomFeedSubsystem;

public class BottomFeed extends CommandBase {
  /**
   * Creates a new BottomFeed.
   */

   private final BottomFeedSubsystem m_FeedSubsystem;

  public BottomFeed(BottomFeedSubsystem m_FeedSubsystem) {
    this.m_FeedSubsystem = m_FeedSubsystem;
    addRequirements(m_FeedSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_FeedSubsystem.runFeed(0.2);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_FeedSubsystem.stopFeed();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
