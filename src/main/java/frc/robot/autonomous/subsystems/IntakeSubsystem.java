package frc.robot.autonomous.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.Motors;

public class IntakeSubsystem extends SubsystemBase {
    // Intake motor
    private final WPI_VictorSPX intakeMotor = Motors.intakeMotor;

    // Intake subsystem
    public IntakeSubsystem() {

    }

    public void runIntake() {
        intakeMotor.set(-0.4);
    }

    public void stopIntake() {
        intakeMotor.set(0);
    }
}