package frc.robot.autonomous.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Motors;

public class IntakeSubsystem extends SubsystemBase {
    // Intake motor
    private final WPI_VictorSPX intakeMotor = Motors.intakeMotor;

    // Intake subsystem
    public IntakeSubsystem() {

    }

    public void stopIntake() {
        intakeMotor.set(0);
    }

    public void reverseIntake() {
        intakeMotor.set(1);
    }

    public void runIntake(XboxController controller) {
        if (Math.abs(controller.getRawAxis(5)) > 0.15) {
            Motors.intakeMotor.set(controller.getRawAxis(5));
        } else {
            Motors.intakeMotor.set(0);
        }
    }
}