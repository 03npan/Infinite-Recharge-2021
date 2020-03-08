/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.autonomous.subsystems.BottomFeedSubsystem;
import frc.robot.autonomous.subsystems.IntakeSubsystem;
import frc.robot.autonomous.subsystems.TopFeedSubsystem;

public class EjectBalls extends CommandBase {
  /**
   * Creates a new EjectBalls.
   */
  private TopFeedSubsystem m_topFeed;
  private BottomFeedSubsystem m_bottomFeed;
  private IntakeSubsystem m_intake;

  public EjectBalls(TopFeedSubsystem m_topFeed, BottomFeedSubsystem m_bottomFeed, IntakeSubsystem m_intake) {
    this.m_topFeed = m_topFeed;
    this.m_bottomFeed = m_bottomFeed;
    this.m_intake = m_intake;
    addRequirements(m_topFeed, m_bottomFeed, m_intake);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_topFeed.reverseFeed();
    m_bottomFeed.reverseFeed();
    m_intake.reverseIntake();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_topFeed.stopFeed();
    m_bottomFeed.stopFeed();
    m_intake.stopIntake();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
