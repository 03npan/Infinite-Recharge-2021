/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.autonomous.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.autonomous.subsystems.FeederSubsystem;

public class Feed extends CommandBase {

    private final FeederSubsystem m_feederSubSystem;

    public Feed(FeederSubsystem subsystem) {
        m_feederSubSystem = subsystem;
        addRequirements(m_feederSubSystem);
    }

    @Override
    public void initialize() {
        m_feederSubSystem.feed();
    }

    @Override
    public void end(boolean interrupted) {
        m_feederSubSystem.stopFeeding();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
