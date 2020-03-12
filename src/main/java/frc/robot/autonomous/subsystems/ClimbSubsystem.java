/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Motors;

public class ClimbSubsystem extends SubsystemBase {
  private final DoubleSolenoid solenoid = Motors.solenoid;
  /**
   * Creates a new PneumaticsSubsystem.
   */
  public ClimbSubsystem() {

  }

  public void liftClimber() {
    solenoid.set(Value.kForward);
  }

  public void climb() {
    solenoid.set(Value.kReverse);
  }

  public void solenoidOff() {
    solenoid.set(Value.kOff);
  }
}
