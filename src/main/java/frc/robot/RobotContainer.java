package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
//import frc.robot.autonomous.commands.Aim;
import frc.robot.autonomous.commands.Feed;
import frc.robot.autonomous.commands.Shoot;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.buttons.Trigger;

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
                .driveRobot(1,1), m_robotDrive)); // finish up driveRobot()
    }

    private void configureButtonBindings() {
        new JoystickButton(dController, XboxController.Button.kA.value)
            .whenPressed(new Shoot(m_shooter));
        
        /* TRY IF THE ABOVE DOESN'T WORK
        final JoystickButton shootButton = new JoystickButton(dController, XboxController.Button.kA.value);
        final JoystickButton feedButton = new JoystickButton(dController, XboxController.Button.kB.value);
        final JoystickButton aimButton = new JoystickButton(dController, XboxController.Button.kX.value);
            
        shootButton.whenPressed(new Shoot(m_shooter));
        */
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