/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous.commands;

import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.autonomous.subsystems.ColorWheelSubsystem;

public class ColorWheel extends CommandBase {
  /**
   * Creates a new SpinColorWheel.
   */

  /**
   * All teams start at stage one Stage 2: Rotate the color wheel a set number of
   * times Stage 3: Rotate the color wheel to a set color
   */

  /**
   * POSITION CONTROL: Rotate CONTROL PANEL so a specified color aligns with the
   * sensor for at least five (5) seconds. Once either ALLIANCE reaches Stage 3
   * CAPACITY, FMS relays a specified color (randomly selected by FMS and one (1)
   * of the three (3) colors not currently read by the ALLIANCE’S TRENCH color
   * sensor) to all OPERATOR CONSOLES simultaneously. The specified color may not
   * be the same for both ALLIANCES. See Table 3-4 for details on how the TRENCH
   * light is used during POSTION CONTROL.
   */

  private final ColorWheelSubsystem m_colorWheel;

  public ColorWheel(ColorWheelSubsystem m_colorWheel) {
    this.m_colorWheel = m_colorWheel;
    addRequirements(m_colorWheel);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_colorWheel.intialize();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_colorWheel.runColorWheel();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_colorWheel.stopColorWheel();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
