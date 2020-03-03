/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous.commands;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.ColorMatch;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Motors;
import frc.robot.utils.Sensors;

public class SpinColorWheel extends CommandBase {
  /**
   * Creates a new SpinColorWheel.
   */

  /**
   * All teams start at stage one Stage 2: Rotate the color wheel a set number of
   * times Stage 3: Rotate the color wheel to a set color
   */

  /**
   * POSITION CONTROL: Rotate CONTROL PANEL so a specified color aligns with the
   * sensor for at least five (5) seconds. Once either ALLIANCE reaches Stage 3
   * CAPACITY, FMS relays a specified color (randomly selected by FMS and one (1)
   * of the three (3) colors not currently read by the ALLIANCE’S TRENCH color
   * sensor) to all OPERATOR CONSOLES simultaneously. The specified color may not
   * be the same for both ALLIANCES. See Table 3-4 for details on how the TRENCH
   * light is used during POSTION CONTROL.
   */

  private final ColorSensorV3 m_colorSensor = Sensors.getColorSensor();
  private final ColorMatch m_colorMatcher = new ColorMatch();

  private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  private String stageOneColor;

  private String stopAtColor;
  private int currentSpin;

  private WPI_VictorSPX colorWheelMotor = Motors.colorWheelMotor;

  private String gameData;

  public SpinColorWheel() {
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_colorMatcher.addColorMatch(kBlueTarget);
    m_colorMatcher.addColorMatch(kGreenTarget);
    m_colorMatcher.addColorMatch(kRedTarget);
    m_colorMatcher.addColorMatch(kYellowTarget);

    Color detectedColor = m_colorSensor.getColor();
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    if (match.color == kBlueTarget) {
      stageOneColor = "B";
    } else if (match.color == kRedTarget) {
      stageOneColor = "R";
    } else if (match.color == kGreenTarget) {
      stageOneColor = "G";
    } else if (match.color == kYellowTarget) {
      stageOneColor = "Y";
    } else {
      stageOneColor = "Unknown";
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    readGameData();

    Color detectedColor = m_colorSensor.getColor();
    ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

    String colorString;
    String stageOneColorString;

    colorString = offsetColorReading(match);
    stageOneColorString = stageOneColorReading(match);

    colorWheelMotor.set(0.5);

    if (stopAtColor.equalsIgnoreCase("Stage_1")) {
      if (currentSpin < 3) { // Minimum number of spins is 3 and maximum number of spins is 5
        currentSpin += 1;
        Timer.delay(0.75);
      } else if ((currentSpin >= 3) && (currentSpin <= 5)) {
        if (stageOneColor.equalsIgnoreCase(stageOneColorString)) {
          end(false);
        }
      }
    }

    if (stopAtColor.equalsIgnoreCase(colorString)) {
      // If the wheel keeps spinning even when the motor stops running, try running
      // the motor back a second or two and then stop
      end(false);
    }

    /**
     * Open Smart Dashboard or Shuffleboard to see the color detected by the sensor.
     */
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    colorWheelMotor.set(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  private void readGameData() {
    // Game data will be empty when in stage one, therefore I set the stopAtColor to
    // Stage_1
    if (gameData.length() > 0) {
      switch (gameData.charAt(0)) {
        case 'B':
          stopAtColor = "R";
          break;
        case 'G':
          stopAtColor = "Y";
          break;
        case 'R':
          stopAtColor = "B";
          break;
        case 'Y':
          stopAtColor = "G";
          break;
        default:
          // This is corrupt data
          break;
      }
    } else {
      stopAtColor = "Stage_1";
    }
  }

  private String offsetColorReading(ColorMatchResult match) {
    String colorString = "";
    // Offset the reading of the colors because the color wheel sensor isn't in the
    // same spot as our robot's color sensor
    if (match.color == kBlueTarget) {
      colorString = "R"; // When we detect blue, the color wheel sensor is detecting red
    } else if (match.color == kRedTarget) {
      colorString = "B"; // When we detect red, the color wheel sensor is detecting blue
    } else if (match.color == kGreenTarget) {
      colorString = "Y"; // When we detect green, the color wheel sensor is detecting yellow
    } else if (match.color == kYellowTarget) {
      colorString = "G"; // When we detect yellow, the color wheel sensor is detecting green
    } else {
      colorString = "Unknown";
    }
    return colorString;
  }

  private String stageOneColorReading(ColorMatchResult match) {
    String colorString = "";
    if (match.color == kBlueTarget) {
      colorString = "B";
    } else if (match.color == kRedTarget) {
      colorString = "R";
    } else if (match.color == kGreenTarget) {
      colorString = "G";
    } else if (match.color == kYellowTarget) {
      colorString = "Y";
    } else {
      colorString = "Unknown";
    }
    return colorString;
  }
}
