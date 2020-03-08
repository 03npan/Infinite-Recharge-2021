/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Motors;

public class TopFeedSubsystem extends SubsystemBase {
  /**
   * Creates a new TopFeedSubsystem.
   */

   WPI_VictorSPX feedMotor = Motors.feederMotorTop;

  public TopFeedSubsystem() {

  }

  public void runFeed(double motorSpeed) {
    feedMotor.set(-motorSpeed);
  }
  
  public void stopFeed() {
    feedMotor.set(0);
  }

  public void reverseFeed() {
    feedMotor.set(1);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
