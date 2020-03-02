package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
    // Intake motor
    private final WPI_VictorSPX intakeMotor = new WPI_VictorSPX(IntakeConstants.INTAKE_MOTOR);

    // Intake subsystem
    public IntakeSubsystem() {

    }

    public void runIntake() {
        intakeMotor.set(IntakeConstants.intake_speed);
    }

    public void stopIntake() {
        intakeMotor.set(0);
    }
}