/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utils.Motors;

public class Shoot extends CommandBase {

    private double motorSpeed;

    public Shoot(double motorSpeed) {
        this.motorSpeed = motorSpeed;
    }

    @Override
    public void initialize() {
        Motors.leadShooterNeo.set(motorSpeed);
    }

    @Override
    public void end(boolean interrupted) {
        Motors.leadShooterNeo.set(0);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
