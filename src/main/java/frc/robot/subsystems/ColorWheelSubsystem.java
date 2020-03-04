package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ColorWheelConstants;

public class ColorWheelSubsystem extends SubsystemBase {
    private final WPI_VictorSPX colorWheelMotor = new WPI_VictorSPX(ColorWheelConstants.COLOR_WHEEL_MOTOR);

    public ColorWheelSubsystem() {

    }

    public void getColor() {

    }
    
    public void turnColorWheel() {

    }

    public void stopColorWheel() {
        
    }
}