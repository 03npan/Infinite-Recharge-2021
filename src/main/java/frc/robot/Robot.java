package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.autonomous.RotateToAngle;

public class Robot extends TimedRobot implements PIDOutput {

  // ---------------------------------------

  private XboxController driverController;
  private XboxController operatorController;

  PIDController turnController;

  static final double kP = 0.03;
  static final double kI = 0.00;
  static final double kD = 0.00;
  static final double kF = 0.00;

  private int maxPsi = 100;
  private int psiThreshold = maxPsi - 2;

  private boolean cannonFiring = false;

  private RotateToAngle rotateToAngle;
  public double rotateToAngleRate;

  static final double kToleranceDegrees = 2.0f;

  static final double kTargetAngleDegrees = 90.0f;

  private Compressor compressor;

  // private UsbCamera camera1;

  private boolean m_LimelightHasValidTarget = false;
  private double m_LimelightDriveCommand = 0.0;
  private double m_LimelightSteerCommand = 0.0;

  @Override
  public void robotInit() {
    Motors.initialize();
    Sensors.initialize();
    turnController = new PIDController(kP, kI, kD, kF, Sensors.navX, this);
    rotateToAngle = new RotateToAngle(Sensors.navX, turnController);

    Motors.drive.setExpiration(0.1);

    turnController.setInputRange(-180.0f, 180.0f);
    turnController.setOutputRange(-1.0, 1.0);
    turnController.setAbsoluteTolerance(kToleranceDegrees);
    turnController.setContinuous(true);
    turnController.disable();

    driverController = new XboxController(1);
    operatorController = new XboxController(1);

    compressor = new Compressor(0);
    compressor.setClosedLoopControl(false);

    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);

    // camera1 = CameraServer.getInstance().startAutomaticCapture();
  }

  @Override
  public void robotPeriodic() {
    displayDashboard();
    // autoCompressorControl();
  }

  @Override
  public void autonomousInit() {
    // Sensors.navX.reset();
  }

  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    //driveControl();
    cannonPivot();
    if (driverController.getAButtonPressed()) {
      cannonFiring = true;
      compressor.setClosedLoopControl(false);
      Motors.cannon_2.set(1.0);
    } else if (driverController.getAButtonReleased()) {
      Motors.cannon_2.set(0);
      cannonFiring = false;
    }
    turnToTarget();
  }

  @Override
  public void testPeriodic() {
  }

  private void driveControl() {
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

  private void reverseDriveControl() {
    double deadZone = 0.15;
    double dif = Math.signum(driverController.getRawAxis(3) - driverController.getRawAxis(2))
        * ((driverController.getRawAxis(3) - driverController.getRawAxis(2))
            * (driverController.getRawAxis(3) - driverController.getRawAxis(2)));
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

  private void cannonPivot() {
    double deadZone = 0.15;
    if (Math.abs(driverController.getRawAxis(5)) > deadZone) {
      Motors.cannonPivot.set(driverController.getRawAxis(5));
    } else {
      Motors.cannonPivot.set(0);
    }
  }

  private void autoCompressorControl() {
    if ((Sensors.pressureSensor.getAirPressurePsi() >= maxPsi) || cannonFiring) {
      compressor.setClosedLoopControl(false);
    } else if (Sensors.pressureSensor.getAirPressurePsi() <= psiThreshold) {
      compressor.setClosedLoopControl(true);
    }
  }

  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  public double getRotateAngleRate() {
    return rotateToAngleRate;
  }

  @Override
  /* This function is invoked periodically by the PID Controller, */
  /* based upon navX MXP yaw angle input and PID Coefficients. */
  public void pidWrite(double output) {
    rotateToAngleRate = output;
  }

  private void displayDashboard() {
    SmartDashboard.putNumber("Current PSI: ", Sensors.pressureSensor.getAirPressurePsi());
    SmartDashboard.putNumber("Current Gyro Angle: ", Sensors.navX.getAngle());
    createSmartDashboardNumber("Max PSI", maxPsi);
    createSmartDashboardNumber("Air Tank PSI Threshold: ", psiThreshold);
  }

  public static double createSmartDashboardNumber(String key, double defValue) {

    // See if already on dashboard, and if so, fetch current value
    double value = SmartDashboard.getNumber(key, defValue);

    // Make sure value is on dashboard, puts back current value if already set
    // otherwise puts back default value
    SmartDashboard.putNumber(key, value);

    return value;
  }

  private void turnToTarget() {

    Update_Limelight_Tracking();

    boolean auto = driverController.getAButton();

    if (auto) {
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
      if (m_LimelightHasValidTarget) {
        Motors.drive.arcadeDrive(m_LimelightDriveCommand, m_LimelightSteerCommand);
      } else {
        Motors.drive.arcadeDrive(0.0, 0.0);
      }
    } else {
      reverseDriveControl();
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    }
  }

  public void Update_Limelight_Tracking() {
    // These numbers must be tuned for your Robot! Be careful!
    final double STEER_K = 0.03; // how hard to turn toward the target
    final double DRIVE_K = 0.26; // how hard to drive fwd toward the target
    final double DESIRED_TARGET_AREA = 13.0; // Area of the target when the robot reaches the wall
    final double MAX_DRIVE = 0.5; // Simple speed limit so we don't drive too fast

    double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
    double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);

    if (tv < 1.0) {
      m_LimelightHasValidTarget = false;
      m_LimelightDriveCommand = 0.0;
      m_LimelightSteerCommand = 0.0;
      return;
    }

    m_LimelightHasValidTarget = true;

    // Start with proportional steering
    double steer_cmd = tx * STEER_K;
    m_LimelightSteerCommand = steer_cmd;

    // try to drive forward until the target area reaches our desired area
    double drive_cmd = (DESIRED_TARGET_AREA - ta) * DRIVE_K;

    // don't let the robot drive too fast into the goal
    if (drive_cmd > MAX_DRIVE) {
      drive_cmd = MAX_DRIVE;
    }
    m_LimelightDriveCommand = drive_cmd;
    }
}