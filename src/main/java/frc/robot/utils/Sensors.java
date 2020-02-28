package frc.robot.utils;

import com.revrobotics.CANEncoder;

/**
 * Sensors
 * Should be initialized AFTER initializing the Motors class
 */
public class Sensors {

    public static CANEncoder leftNeo;
    //public static CANEncoder rightNeo;

    public static void initialize() {
        leftNeo = new CANEncoder(Motors.leadShooterNeo);
        //rightNeo = new CANEncoder(Motors.followerShooterNeo);
    }
}