package frc.robot.autonomous;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import frc.robot.Motors;
import frc.robot.Robot;

/**
 * RotateToAngle
 */
public class RotateToAngle {

    private Robot robot = new Robot();

    AHRS ahrs;
    PIDController turnController;
    DifferentialDrive myRobot;
    double rotateToAngleRate = robot.getRotateAngleRate();

    public RotateToAngle(AHRS ahrs, PIDController turnController) {
        this.ahrs = ahrs;
        this.turnController = turnController;
        myRobot = Motors.drive;
    }

    static final double kToleranceDegrees = 2.0f;

    static final double kTargetAngleDegrees = 90.0f;

    // Channels for the wheels
    final static int leftChannel = 0;
    final static int rightChannel = 1;

    WPI_VictorSPX leftMotor;
    WPI_VictorSPX rightMotor;

    public void operatorControl(XboxController operatorController, XboxController driverController) {
        if (operatorController.getAButtonPressed()) {
            System.out.println("1");
            if (!turnController.isEnabled()) {
                System.out.println("2");
                turnController.setSetpoint(kTargetAngleDegrees);
                System.out.println("3");
                rotateToAngleRate = 0; // This value will be updated in the pidWrite() method.
                turnController.enable();
                System.out.println("4");
            }
            System.out.println("5");
            double leftStickValue = rotateToAngleRate;
            double rightStickValue = rotateToAngleRate;
            System.out.println("6");
            myRobot.tankDrive(leftStickValue, rightStickValue);
            System.out.println("7. Current Rotate Angle Rate: " + rotateToAngleRate);
        } else if (operatorController.getXButtonPressed()) {
            /*
             * "Zero" the yaw (whatever direction the sensor is pointing now will become the
             * new "Zero" degrees.
             */
            ahrs.zeroYaw();
        } else if (operatorController.getYButtonPressed()) {
            /*
             * While this button is held down, the robot is in "drive straight" mode.
             * Whatever direction the robot was heading when "drive straight" mode was
             * entered will be maintained. The average speed of both joysticks is the
             * magnitude of motion.
             */
            if (!turnController.isEnabled()) {
                // Acquire current yaw angle, using this as the target angle.
                turnController.setSetpoint(ahrs.getYaw());
                rotateToAngleRate = 0; // This value will be updated in the pidWrite() method.
                turnController.enable();
            }
            double magnitude = (driverController.getRawAxis(2) + driverController.getRawAxis(3)) / 2;
            double leftStickValue = magnitude + rotateToAngleRate;
            double rightStickValue = magnitude - rotateToAngleRate;
            myRobot.tankDrive(leftStickValue, rightStickValue);
        } else {
            /* If the turn controller had been enabled, disable it now. */
            if (turnController.isEnabled()) {
                turnController.disable();
            }
            /* Standard arcade drive, no driver assistance. */
            driveControl(driverController);
        }
        Timer.delay(0.005); // wait for a motor update time
    }

    private void driveControl(XboxController driverController) {
        double deadZone = 0.15;
        double dif = Math.signum(driverController.getRawAxis(2) - driverController.getRawAxis(3))
            * ((driverController.getRawAxis(2) - driverController.getRawAxis(3))
                * (driverController.getRawAxis(2) - driverController.getRawAxis(3)));
        if (Math.abs(dif) <= deadZone)
          dif = 0.0;
    
        double turn = Math.signum(driverController.getRawAxis(0))
            * (driverController.getRawAxis(0) * driverController.getRawAxis(0));
        if (Math.abs(turn) <= deadZone)
          turn = 0.0;
    
        Motors.drive.arcadeDrive(dif * 1, (turn) * 0.6); // 0.8
        // Fist num: Driving speed
        // Second num: Turning speed
      }
}