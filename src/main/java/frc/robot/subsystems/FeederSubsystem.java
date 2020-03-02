//maybe this should be combined into shooter/intake subsystem

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.FeederConstants;

public class FeederSubsystem extends SubsystemBase {
    // Feeder motors
    private final WPI_VictorSPX feederMotorBottom = new WPI_VictorSPX(FeederConstants.FEEDER_MOTOR_BOTTOM);
    private final WPI_VictorSPX feederMotorTop = new WPI_VictorSPX(FeederConstants.FEEDER_MOTOR_TOP);

    // Feeder subsystem
    public FeederSubsystem() {

    }

    public void runFeeder() {
        feederMotorBottom.set(FeederConstants.feeder_speed);
        feederMotorTop.set(FeederConstants.feeder_speed);
    }

    public void stopFeeder() {
        feederMotorBottom.set(0);
        feederMotorTop.set(0);
    }
}