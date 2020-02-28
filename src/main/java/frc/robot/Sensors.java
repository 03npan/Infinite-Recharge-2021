package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SPI;
import frc.utils.RevRoboticsAirPressureSensor;

/**
 * Similar to {@link Motors}, this class stores all of the sensors to make them easier to find.
 * @author Alex Plisov
 *
 */
public class Sensors {
	
	public static AHRS navX;
	public static RevRoboticsAirPressureSensor pressureSensor;
	
	public static void initialize() {
		navX = new AHRS(SPI.Port.kMXP);
		pressureSensor = new RevRoboticsAirPressureSensor(0);
	}
}
