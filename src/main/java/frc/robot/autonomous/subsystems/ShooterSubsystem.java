/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous.subsystems;

import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Motors;

public class ShooterSubsystem extends SubsystemBase {
  /**
   * Creates a new ShooterSubsystem.
   */

  CANSparkMax leadNeo = Motors.leadShooterNeo;
  CANSparkMax followerNeo = Motors.followerShooterNeo;

  public ShooterSubsystem() {
    leadNeo.restoreFactoryDefaults();
    followerNeo.restoreFactoryDefaults();
    followerNeo.follow(leadNeo, true);
  }

  public void runShooter(double motorSpeed) {
    leadNeo.set(motorSpeed);
  }

  public void stopShooter() {
    leadNeo.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

