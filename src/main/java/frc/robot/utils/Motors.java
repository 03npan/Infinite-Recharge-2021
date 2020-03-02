package frc.robot.utils;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * Motors
 */
public class Motors {

    // ----------------------------- Driving Motors -----------------------------

    private static WPI_VictorSPX victorLeft1;
    private static WPI_VictorSPX victorRight1;
    private static WPI_VictorSPX victorLeft2;
    private static WPI_VictorSPX victorRight2;
    private static SpeedControllerGroup left;
    private static SpeedControllerGroup right;
    public static DifferentialDrive drive;

    public static WPI_VictorSPX intakeMotor;

    public static WPI_VictorSPX feederMotorBottom;
    public static WPI_VictorSPX feederMotorTop;

    public static CANSparkMax leadShooterNeo;
    public static CANSparkMax followerShooterNeo;

    public static void initialize() {
        victorLeft1 = new WPI_VictorSPX(RobotMap.VICTOR_LEFT_1.getPin());
        victorRight1 = new WPI_VictorSPX(RobotMap.VICTOR_RIGHT_1.getPin());
        victorLeft2 = new WPI_VictorSPX(RobotMap.VICTOR_LEFT_2.getPin());
        victorRight2 = new WPI_VictorSPX(RobotMap.VICTOR_RIGHT_2.getPin());

        left = new SpeedControllerGroup(victorLeft1, victorLeft2);
        right = new SpeedControllerGroup(victorRight1, victorRight2);
        drive = new DifferentialDrive(left, right);
        drive.setSafetyEnabled(false);

        intakeMotor = new WPI_VictorSPX(RobotMap.INTAKE_MOTOR.getPin());

        feederMotorBottom = new WPI_VictorSPX(RobotMap.FEEDER_MOTOR_BOTTOM.getPin());
        feederMotorTop = new WPI_VictorSPX(RobotMap.FEEDER_MOTOR_TOP.getPin());

        leadShooterNeo = new CANSparkMax(RobotMap.LEAD_SHOOTER_NEO.getPin(), MotorType.kBrushless);
        followerShooterNeo = new CANSparkMax(RobotMap.FOLLOWER_SHOOTER_NEO.getPin(), MotorType.kBrushless);

        leadShooterNeo.restoreFactoryDefaults();
        followerShooterNeo.restoreFactoryDefaults();

        followerShooterNeo.follow(leadShooterNeo);

        left = new SpeedControllerGroup(victorLeft1, victorLeft2);
        right = new SpeedControllerGroup(victorRight1, victorRight2);
        drive = new DifferentialDrive(left, right);
        drive.setSafetyEnabled(false);
    }
}