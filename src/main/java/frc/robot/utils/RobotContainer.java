package frc.robot.utils;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autonomous.commands.Aim;
import frc.robot.autonomous.commands.ColorWheel;
import frc.robot.autonomous.commands.groups.Shoot;
import frc.robot.autonomous.subsystems.AimSubsystem;
import frc.robot.autonomous.subsystems.BottomFeedSubsystem;
import frc.robot.autonomous.subsystems.ColorWheelSubsystem;
import frc.robot.autonomous.subsystems.DriveSubsystem;
import frc.robot.autonomous.subsystems.ShooterSubsystem;
import frc.robot.autonomous.subsystems.TopFeedSubsystem;

/**
 * RobotContainer Sets up all the commands
 */
public class RobotContainer {

    // The robot's subsystems
    private final DriveSubsystem m_robotDrive = new DriveSubsystem();
    // private final IntakeSubsystem m_intake = new IntakeSubsystem();
    private final ShooterSubsystem m_shooter = new ShooterSubsystem();
    private final TopFeedSubsystem m_topFeeder = new TopFeedSubsystem();
    private final BottomFeedSubsystem m_bottomFeeder = new BottomFeedSubsystem();
    // private final ClimbSubsystem m_climb = new ClimbSubsystem();
    private final ColorWheelSubsystem m_colorWheel = new ColorWheelSubsystem();
    private final AimSubsystem m_aim = new AimSubsystem();

    // Controllers
    public static XboxController dController = new XboxController(0);
    public static XboxController oController = new XboxController(1);

    // Autonomous routine (Default command)
    private final Command m_autoCommand = null; // ADD

    final JoystickButton shootButton = new JoystickButton(dController, XboxController.Button.kA.value);
    final JoystickButton aimButton = new JoystickButton(dController, XboxController.Button.kX.value);
    final JoystickButton spinColorWheelButton = new JoystickButton(dController, XboxController.Button.kB.value);

    public RobotContainer() {

        shootButton.whenHeld(new Shoot());
        aimButton.whenHeld(new Aim(m_aim));
        spinColorWheelButton.whenHeld(new ColorWheel(m_colorWheel));

        // Default command set to drive
        m_robotDrive.setDefaultCommand(new RunCommand(() -> m_robotDrive.driveRobot(dController), m_robotDrive));
    }

    public void runIntake() {
        if (Math.abs(oController.getRawAxis(5)) > 0.15) {
            Motors.intakeMotor.set(oController.getRawAxis(5));
        } else {
            Motors.intakeMotor.set(0);
        }
    }
}
