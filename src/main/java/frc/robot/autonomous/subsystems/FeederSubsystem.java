package frc.robot.autonomous.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Motors;

/**
 * FeederSubsystem
 */
public class FeederSubsystem extends SubsystemBase {

    public void feed() {
        Motors.feederMotorBottom.set(1.0);
        Motors.feederMotorTop.set(1.0);
    }

    public void stopFeeding() {
        Motors.feederMotorBottom.set(0);
        Motors.feederMotorTop.set(0);
    }
}