/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous.subsystems;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Motors;

public class DriveStraightSubsystem extends SubsystemBase {
  /**
   * Creates a new DriveStraightSubsystem.
   */
  Timer timer = new Timer();
  public DriveStraightSubsystem() {

  }

  public void driveStraight(double time, double speed) {
    timer.start(); // starts a timer at 0 seconds
    if (timer.get() < time) // time is a double in terms of seconds
    {
      Motors.drive.arcadeDrive(speed, 0); // move robot at this speed with/without curve
    }
    Timer.delay(0.005); // does nothing for 5 seconds but helps refresh motors in loop
    timer.stop(); // stops timer
  }

  public void stopDrivingStraight() {
    Motors.drive.arcadeDrive(0, 0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
