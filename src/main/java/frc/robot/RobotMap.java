package frc.robot;

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
	VICTOR_RIGHT_1(2),
	VICTOR_LEFT_2(1),
	VICTOR_RIGHT_2(3),

	PIVOT_CANNON(4),
	
	CANNON_2(5);
	
	private int pinNum;
	RobotMap(int num) {
		pinNum = num;
	}
	
	public int getPin() {
		return pinNum;
	}
}
