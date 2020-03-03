package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Motors;

/**
 * DriveStraight
 */

public class DriveStraight extends CommandBase {
    /**
     * Creates a new DriveStraight.
     */

    private Timer timer = new Timer();;

    private double motorSpeed;

    private DifferentialDrive drive = Motors.drive;

    public DriveStraight(double motorSpeed) {
        this.motorSpeed = motorSpeed;
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        driveStraight(4);
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

    private void driveStraight(double seconds) {
        timer.start(); // starts a timer at 0 seconds
        while (!isFinished()) // run when autonomous is Clive and you are enabled
        {
            if (timer.get() < seconds) // time is a double in terms of seconds
            {
                drive.arcadeDrive(motorSpeed, 0); // move robot at this speed with/without curve
            } else {
                drive.arcadeDrive(0, 0);
                end(false);
            }
            Timer.delay(0.005); // does nothing for 5 seconds but helps refresh motors in loop
        }
        timer.stop(); // stops timer
    }

}