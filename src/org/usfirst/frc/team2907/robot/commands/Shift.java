package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

public class Shift extends Command {
	public Shift() {
	}

	protected void initialize() {
		DriveTrain.getInstance().shift(!DriveTrain.getInstance().isHighGear);
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
		end();
	}
}