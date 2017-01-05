package org.usfirst.frc.team2907.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;


public class NonLowBarAuto extends CommandGroup {
    public NonLowBarAuto() {
    	addSequential(new DriveForTime(-0.6, 0.0, 7.0));
    }
}
