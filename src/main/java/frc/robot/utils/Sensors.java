package frc.robot.utils;

import com.revrobotics.CANEncoder;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;

/**
 * Sensors Should be initialized AFTER initializing the Motors class
 */
public class Sensors {

    private static CANEncoder leftNeo;
    private static CANEncoder rightNeo;

    private static final I2C.Port i2cPort = I2C.Port.kOnboard;
    private static ColorSensorV3 colorSensor;

    public static void initialize() {
        leftNeo = new CANEncoder(Motors.leadShooterNeo);
        rightNeo = new CANEncoder(Motors.followerShooterNeo);
        colorSensor = new ColorSensorV3(i2cPort);
    }

    /**
     * @return the leftNeo
     */
    public static CANEncoder getLeftNeo() {
        return leftNeo;
    }

    /**
     * @return the rightNeo
     */
    public static CANEncoder getRightNeo() {
        return rightNeo;
    }

    /**
     * @return the colorSensor
     */
    public static ColorSensorV3 getColorSensor() {
        return colorSensor;
    }

}