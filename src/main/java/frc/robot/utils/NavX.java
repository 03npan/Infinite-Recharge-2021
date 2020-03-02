/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.utils;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;

/**
 * Add your docs here.
 */
public class NavX implements PIDOutput {

    public AHRS ahrs;

    private final double kP = 0.03;
    private final double kI = 0.00;
    private final double kD = 0.00;
    private final double kF = 0.00;

    private final double kToleranceDegrees = 2.0f;

    public double rotateToAngleRate;


    private PIDController turnController;

    public void initialize() {
        try {
            ahrs = new AHRS(SPI.Port.kMXP);
        } catch (RuntimeException e) {
            DriverStation.reportError("Error intantiating navX-MXP: " + e.getMessage(), true);
        }

        turnController = new PIDController(kP, kI, kD, kF, ahrs, this);
        turnController.setInputRange(-180.0f,  180.0f);
        turnController.setOutputRange(-1.0, 1.0);
        turnController.setAbsoluteTolerance(kToleranceDegrees);
        turnController.setContinuous(true);
        turnController.disable();
    }

    public AHRS getAHRS() {
        return ahrs;
    }

    public double getkToleranceDegrees() {
        return kToleranceDegrees;
    }

    /**
     * @return the rotateToAngleRate
     */
    public double getRotateToAngleRate() {
        return rotateToAngleRate;
    }

    /**
     * @return the turnController
     */
    public PIDController getTurnController() {
        return turnController;
    }

    @Override
    public void pidWrite(double output) {
        rotateToAngleRate = output;

    }
}
