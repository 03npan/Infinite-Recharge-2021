package frc.robot.utils;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autonomous.commands.Aim;
import frc.robot.autonomous.commands.SpinColorWheel;
import frc.robot.autonomous.commands.groups.Shoot;

/**
 * OI
 */
public class OI {

    public static XboxController dController = new XboxController(0);
    public static XboxController oController = new XboxController(1);

    final JoystickButton shootButton = new JoystickButton(dController, XboxController.Button.kA.value);
    final JoystickButton aimButton = new JoystickButton(dController, XboxController.Button.kX.value);
    final JoystickButton spinColorWheelButton = new JoystickButton(dController, XboxController.Button.kB.value);

    public OI() {

        shootButton.whenActive(new Shoot());
        aimButton.whenHeld(new Aim());
        spinColorWheelButton.whenHeld(new SpinColorWheel());
    }

    public void runIntake() {
        if (Math.abs(oController.getRawAxis(5)) > 0.15) {
            Motors.intakeMotor.set(oController.getRawAxis(5));
        } else {
            Motors.intakeMotor.set(0);
        }
    }
}
