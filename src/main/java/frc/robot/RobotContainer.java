package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.autonomous.commands.*;
import frc.robot.subsystems.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    // The robot's subsystems
    private final DriveSubsystem m_robotDrive = new DriveSubsystem();
    private final IntakeSubsystem m_intake = new IntakeSubsystem();
    private final ShooterSubsystem m_shooter = new ShooterSubsystem();
    private final FeederSubsystem m_feeder = new FeederSubsystem();
    private final ClimbSubsystem m_climb = new ClimbSubsystem();
    private final ColorWheelSubsystem m_colorWheel = new ColorWheelSubsystem();

    // Controllers
    public XboxController dController = new XboxController(0);
    public XboxController oController = new XboxController(1);

    // Autonomous routine
    private final Command m_autoCommand = null; // ADD

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();

        // Default command set to drive
        m_robotDrive.setDefaultCommand(
            new RunCommand(() -> m_robotDrive
                .driveRobot(dController), m_robotDrive)); // finish up driveRobot()
    }

    private void configureButtonBindings() {
        new JoystickButton(dController, XboxController.Button.kA.value)
            .whenHeld(new Shoot(m_shooter));

        new JoystickButton(dController, XboxController.Button.kY.value)
            .whenHeld(new Intake(m_intake));

        new JoystickButton(dController, XboxController.Button.kB.value)
            .whenHeld(new TopFeed(m_feeder));

        new JoystickButton(dController, XboxController.Button.kX.value)
            .whenHeld(new BottomFeed(m_feeder));
    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     
    public Command getAutonomousCommand() {
        An ExampleCommand will run in autonomous
        return m_autoCommand;
    }*/
}