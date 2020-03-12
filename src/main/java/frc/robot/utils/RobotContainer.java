package frc.robot.utils;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autonomous.commands.Aim;
import frc.robot.autonomous.commands.BottomFeed;
import frc.robot.autonomous.commands.Climb;
import frc.robot.autonomous.commands.DriveStraight;
import frc.robot.autonomous.commands.EjectBalls;
import frc.robot.autonomous.commands.LiftClimber;
import frc.robot.autonomous.commands.Shooter;
import frc.robot.autonomous.commands.TopFeed;
import frc.robot.autonomous.commands.groups.auto.GoalAuto2;
import frc.robot.autonomous.commands.groups.auto.ShootDrivebackP;
import frc.robot.autonomous.subsystems.AimSubsystem;
import frc.robot.autonomous.subsystems.BottomFeedSubsystem;
import frc.robot.autonomous.subsystems.ClimbSubsystem;
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
    private final TopFeedSubsystem m_topFeeder = new TopFeedSubsystem();
    private final BottomFeedSubsystem m_bottomFeeder = new BottomFeedSubsystem();
    // private final ClimbSubsystem m_climb = new ClimbSubsystem();
    private final AimSubsystem m_aim = new AimSubsystem();
    private final ClimbSubsystem m_climb = new ClimbSubsystem();

    // Controllers
    public static XboxController dController = new XboxController(0);
    public static XboxController oController = new XboxController(1);

    private final SendableChooser<Command> m_autoChooser = new SendableChooser<>();
    // Autonomous routine (Default command)
    private final Command m_autoCommand = new GoalAuto2(m_shooter, m_robotDrive); // ADD

    final JoystickButton shootButton = new JoystickButton(dController, XboxController.Button.kA.value);
    final JoystickButton aimButton = new JoystickButton(dController, XboxController.Button.kB.value);
    final JoystickButton testAuto1 = new JoystickButton(dController, XboxController.Button.kX.value);
    final JoystickButton testAuto2 = new JoystickButton(dController, XboxController.Button.kY.value);
    final JoystickButton liftClimber = new JoystickButton(dController, XboxController.Button.kBumperLeft.value);
    final JoystickButton climb = new JoystickButton(dController, XboxController.Button.kBumperRight.value);
    
    final JoystickButton bottomFeederButton = new JoystickButton(oController, XboxController.Button.kA.value);
    final JoystickButton topFeederButton = new JoystickButton(oController, XboxController.Button.kY.value);
    // final JoystickButton intakeButton = new JoystickButton(oController,
    // XboxController.Button.kY.value);
    final JoystickButton colorWheelButton = new JoystickButton(oController, XboxController.Button.kBumperLeft.value);
    final JoystickButton ejectButton = new JoystickButton(oController, XboxController.Button.kB.value);

    public RobotContainer() {

        shootButton.whenHeld(new Shooter(m_shooter, 0.5));
        aimButton.whenHeld(new Aim(m_aim));
        bottomFeederButton.whenHeld(new BottomFeed(m_bottomFeeder));
        topFeederButton.whenHeld(new TopFeed(m_topFeeder));
        ejectButton.whenHeld(new EjectBalls(m_topFeeder, m_bottomFeeder, m_intake));

        testAuto1.whenPressed(new ShootDrivebackP(m_shooter, m_robotDrive));
        testAuto2.whenPressed(new ShootDrivebackP(m_shooter, m_robotDrive));

        liftClimber.whenHeld(new LiftClimber(m_climb));
        climb.whenHeld(new Climb(m_climb));

        // Default command set to drive
        m_robotDrive.setDefaultCommand(new RunCommand(() -> m_robotDrive.driveRobot(dController), m_robotDrive));

        m_autoChooser.setDefaultOption("DriveStraight", new DriveStraight(m_robotDrive, 0.5).withTimeout(2));
        m_autoChooser.addOption("DriveShoot", m_autoCommand);

        Shuffleboard.getTab("ChooserTab").add(m_autoChooser);
    }

    public Command getAutoCommand() {
        return m_autoChooser.getSelected();
    }
}
