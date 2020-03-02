package frc.robot.utils;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autonomous.commands.Aim;
import frc.robot.autonomous.commands.Shoot;

/**
 * OI
 */
public class OI {

    public XboxController dController = new XboxController(0);
    public XboxController oController = new XboxController(1);

    public OI() {
        final JoystickButton shootButton = new JoystickButton(dController, XboxController.Button.kA.value);
        final JoystickButton aimButton = new JoystickButton(dController, XboxController.Button.kX.value);

        shootButton.whenActive(new Shoot());
        aimButton.whenActive(new Aim());
    }

    public void runIntake() {
        if (Math.abs(oController.getRawAxis(5)) > 0.15) {
            Motors.intakeMotor.set(oController.getRawAxis(5));
        } else {
            Motors.intakeMotor.set(0);
        }
    }
}
