package frc.robot.utils;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autonomous.commands.BottomFeed;
import frc.robot.autonomous.commands.ColorWheel;
import frc.robot.autonomous.commands.Intake;
import frc.robot.autonomous.commands.Shooter;
import frc.robot.autonomous.commands.TopFeed;
import frc.robot.autonomous.commands.groups.ShootAuto;
import frc.robot.autonomous.subsystems.AimSubsystem;
import frc.robot.autonomous.subsystems.BottomFeedSubsystem;
import frc.robot.autonomous.subsystems.ColorWheelSubsystem;
import frc.robot.autonomous.subsystems.DriveSubsystem;
import frc.robot.autonomous.subsystems.IntakeSubsystem;
import frc.robot.autonomous.subsystems.ShooterSubsystem;
import frc.robot.autonomous.subsystems.TopFeedSubsystem;

/**
 * RobotContainer Sets up all the commands
 */
public class RobotContainer {

    // The robot's subsystems
    private final DriveSubsystem m_robotDrive = new DriveSubsystem();
    private final IntakeSubsystem m_intake = new IntakeSubsystem();
    private final ShooterSubsystem m_shooter = new ShooterSubsystem();
    private final TopFeedSubsystem m_topFeed = new TopFeedSubsystem();
    private final BottomFeedSubsystem m_bottomFeed = new BottomFeedSubsystem();
    // private final ClimbSubsystem m_climb = new ClimbSubsystem();
    private final ColorWheelSubsystem m_colorWheel = new ColorWheelSubsystem();
    private final AimSubsystem m_aim = new AimSubsystem();

    // Controllers
    public static XboxController dController = new XboxController(0);
    public static XboxController oController = new XboxController(1);

    // Autonomous routine (Default command)
    private final Command m_autoCommand = null; // ADD

    // Driver's controls
    final JoystickButton shootButton = new JoystickButton(dController, XboxController.Button.kA.value);
    // final JoystickButton climbButton = new JoystickButton(dController,
    // XboxController.Button.kB.value);

    // Operator's controls
    final JoystickButton bottomFeederButton = new JoystickButton(dController, XboxController.Button.kX.value);
    final JoystickButton topFeederButton = new JoystickButton(dController, XboxController.Button.kB.value);
    final JoystickButton intakeButton = new JoystickButton(dController, XboxController.Button.kY.value);
    final JoystickButton colorWheelButton = new JoystickButton(dController, XboxController.Button.kBumperLeft.value);

    public RobotContainer() {

        // Driver's controls
        // shootButton.whenHeld(new Shooter(m_shooter)); // Just the shooter
        shootButton.whenHeld(new ShootAuto(m_shooter, m_topFeed, m_bottomFeed)); // The feeder system plus the shooter

        // Operator's controls
        bottomFeederButton.whenHeld(new BottomFeed(m_bottomFeed));
        topFeederButton.whenHeld(new TopFeed(m_topFeed));
        intakeButton.whenHeld(new Intake(m_intake));
        colorWheelButton.whenHeld(new ColorWheel(m_colorWheel));

        // Default command set to drive
        m_robotDrive.setDefaultCommand(new RunCommand(() -> m_robotDrive.driveRobot(dController), m_robotDrive));
    }
}
