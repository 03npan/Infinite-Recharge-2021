package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Motors;

/**
 * DriveStraight
 */
public class DriveStraight extends CommandBase {
    /**
     * Creates a new DriveStraight.
     */

    private double motorSpeed;

    public DriveStraight(double motorSpeed) {
        this.motorSpeed = motorSpeed;
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        Motors.intakeMotor.set(motorSpeed);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        Motors.intakeMotor.set(motorSpeed);
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        Motors.intakeMotor.set(0);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    private void driveStraight() {
        Timer.start(); // starts a timer at 0 seconds
        while (isAutonomous() && isEnabled()) // run when autonomous is Clive and you are enabled
        {
            if (Timer.get() < time) // time is a double in terms of seconds
            {
                driveVariable.drive(speed, curve); // move robot at this speed with/without curve
            }
            Timer.delay(0.005); // does nothing for 5 seconds but helps refresh motors in loop
        }
        Timer.stop(); // stops timer
    }

}