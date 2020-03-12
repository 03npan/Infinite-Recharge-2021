package frc.robot.utils;

import com.revrobotics.CANEncoder;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;

import edu.wpi.first.wpilibj.AnalogInput;

/**
 * Sensors Should be initialized AFTER initializing the Motors class
 */
public class Sensors {

    private static CANEncoder leftNeo;
    private static CANEncoder rightNeo;

    private static final I2C.Port i2cPort = I2C.Port.kOnboard;
    private static ColorSensorV3 colorSensor;
    
    private static AnalogInput pressureSensor;

    public static void initialize() {
        leftNeo = new CANEncoder(Motors.leadShooterNeo);
        rightNeo = new CANEncoder(Motors.followerShooterNeo);
        colorSensor = new ColorSensorV3(i2cPort);
        pressureSensor = new AnalogInput(0);
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

    public static AnalogInput getPressureSensor() {
        return pressureSensor;
    }
}