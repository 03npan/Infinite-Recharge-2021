package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
//import com.revrobotics.CANEncoder;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ShooterConstants;

public class ShooterSubsystem extends SubsystemBase { // PIDSubsystem instead?
    // Shooter motors
    private final CANSparkMax leadShooterNeo = new CANSparkMax(ShooterConstants.LEAD_SHOOTER_NEO, MotorType.kBrushless);
    private final CANSparkMax followerShooterNeo = new CANSparkMax(ShooterConstants.FOLLOWER_SHOOTER_NEO, MotorType.kBrushless);
    //private final CANEncoder leftNeo = new CANEncoder(leadShooterNeo);
    //private final CANEncoder rightNeo = new CANEncoder(followerShooterNeo);

    // Shooter subsystem
    public ShooterSubsystem() {
        leadShooterNeo.restoreFactoryDefaults();
        followerShooterNeo.restoreFactoryDefaults();
        followerShooterNeo.follow(leadShooterNeo, true);
    }

    public void runShooter() {
        leadShooterNeo.set(ShooterConstants.shooter_speed);
    }
    /*
    TESTING PURPOSES
    // rawAxis = m_robotContainer.dController.getRawAxis(5)
    public void runShooter(double rawAxis) {
        if (Math.abs(rawAxis) > 0.15) {
            leadShooterNeo.set(rawAxis);
        } else {
            leadShooterNeo.set(0);
        }
    }
    */
    public void stopShooter() {
        leadShooterNeo.set(0);
    }
}