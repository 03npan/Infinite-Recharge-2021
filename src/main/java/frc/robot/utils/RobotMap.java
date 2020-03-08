package frc.robot.utils;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier 
 * and significantly reduces the number of magic numbers
 * floating around.
 * @author Alex Plisov
 */

 /*
	5 = #1
	4 = #14
	3 = #12
	2 = #13
	1 = #2
	0 = #3
 */
public enum RobotMap {
	// DRIVE MOTORS
	VICTOR_LEFT_1(0),
	VICTOR_RIGHT_1(4),
	VICTOR_LEFT_2(1),
	VICTOR_RIGHT_2(5),

	SHROUD(10),
	INTAKE_MOTOR(2),
	COLOR_WHEEL_MOTOR(3),
    LEAD_SHOOTER_NEO(6),
    FOLLOWER_SHOOTER_NEO(7),
    FEEDER_MOTOR_BOTTOM(8),
    FEEDER_MOTOR_TOP(9);
	
	private int pinNum;
	RobotMap(int num) {
		pinNum = num;
	}
	
	public int getPin() {
		return pinNum;
	}
}
