package frc.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * @author Alex Plisov
 */
public class Motors {
	
	// ----------------------------- Driving Motors -----------------------------
	
	private static WPI_VictorSPX victorLeft1;
	private static WPI_VictorSPX victorRight1;
	private static WPI_VictorSPX victorLeft2;
	private static WPI_VictorSPX victorRight2;
	public static WPI_VictorSPX cannonPivot;
	public static WPI_VictorSPX cannon_2;
	private static SpeedControllerGroup left;
	private static SpeedControllerGroup right;
	public static DifferentialDrive drive;

	public static void initialize() {
		// ----------------------------- Driving Motors -----------------------------
		victorLeft1 = new WPI_VictorSPX(RobotMap.VICTOR_LEFT_1.getPin());
		victorRight1 = new WPI_VictorSPX(RobotMap.VICTOR_RIGHT_1.getPin());
		victorLeft2 = new WPI_VictorSPX(RobotMap.VICTOR_LEFT_2.getPin());
		victorRight2 = new WPI_VictorSPX(RobotMap.VICTOR_RIGHT_2.getPin());
		cannonPivot = new WPI_VictorSPX(RobotMap.PIVOT_CANNON.getPin());
		cannon_2 = new WPI_VictorSPX(RobotMap.CANNON_2.getPin());
		left = new SpeedControllerGroup(victorLeft1, victorLeft2);
		right = new SpeedControllerGroup(victorRight1, victorRight2);
		drive = new DifferentialDrive(left, right);
		drive.setSafetyEnabled(false);
	}
}