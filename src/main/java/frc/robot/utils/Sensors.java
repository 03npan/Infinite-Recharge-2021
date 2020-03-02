package frc.robot.utils;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANEncoder;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.controller.PIDController;

/**
 * Sensors Should be initialized AFTER initializing the Motors class
 */
public class Sensors {

    public static CANEncoder leftNeo;
    public static CANEncoder rightNeo;

    public static void initialize() {
        leftNeo = new CANEncoder(Motors.leadShooterNeo);
        rightNeo = new CANEncoder(Motors.followerShooterNeo);
    }

}