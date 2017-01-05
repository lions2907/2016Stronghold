package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.subsystems.Collector;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveArm extends Command {
	boolean up;

	public MoveArm(boolean up, double time) {
		super(time);
		requires(Collector.getInstance());
		this.up = up;
	}

	protected void initialize() {
		if (up) {
			Collector.getInstance().setArmPower(0.6);
		} else {
			Collector.getInstance().setArmPower(-0.4);
		}
	}

	protected void execute() {
	}

	protected boolean isFinished() {
		return isTimedOut();
	}

	protected void end() {
		Collector.getInstance().setArmPower(0);
	}

	protected void interrupted() {
		end();
	}
}
