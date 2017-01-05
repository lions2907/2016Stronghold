package org.usfirst.frc.team2907.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class LowBarAuto extends CommandGroup {
	public LowBarAuto() {
		addSequential(new MoveArm(false, 0.75));
		addSequential(new DriveForTime(0.6, 0.0, 7.0));
	}
}
