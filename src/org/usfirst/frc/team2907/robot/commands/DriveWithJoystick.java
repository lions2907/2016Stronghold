package org.usfirst.frc.team2907.robot.commands;

import org.usfirst.frc.team2907.robot.Robot;
import org.usfirst.frc.team2907.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command allows PS3 joystick to drive the robot. It is always running
 * except when interrupted by another command.
 */
public class DriveWithJoystick extends Command {
	public DriveWithJoystick() {
		requires(Robot.drivetrain);
	}

	protected void initialize() {
	}

	protected void execute() {
		DriveTrain.getInstance().arcadeDrive(Robot.oi.driverJoystick);
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
		end();
	}
}