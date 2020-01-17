package frc.robot;

/**
 * An Enum used to communicate between the {@link MovementController} and
 * {@link Robot} classes so the {@link MovementController} class knows what is
 * going on
 */
public enum RoboState {
	AutonomousInit("AutonomousInit"), TeleopInit("TeleopInit"), RobotInit("RobotInit"), Teleop("Teleop"),
	Autonomous("Autonomous"), Disabled("Disabled");

	String state;

	RoboState(String state) {
		this.state = state;
	}

	public String toString() {
		return state;
	}
}
