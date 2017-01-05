package org.usfirst.frc.team2907.robot.subsystems;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Shooter extends Subsystem {
	// Devices
	private SpeedController shooterWheel1, shooterWheel2, shooterTrigger, shooterAngle1, shooterAngle2;

	private static Shooter instance = new Shooter();

	public static Shooter getInstance() {
		return instance;
	}

	private Shooter() {
		shooterWheel1 = new CANTalon(13);
		shooterWheel2 = new CANTalon(14);
		shooterWheel1.setInverted(true);
		shooterTrigger = new CANTalon(12);
		shooterAngle1 = new CANTalon(10);
		shooterAngle2 = new CANTalon(15);
		shooterAngle1.setInverted(true);
	}

	public void setShooterSpeed(double speed) {
		shooterWheel1.set(speed);
		shooterWheel2.set(speed);
	}

	public void setTriggerSpeed(double speed) {
		shooterTrigger.set(speed);
	}

	public void setShooterAngle(double power) {
		shooterAngle1.set(power);
		shooterAngle2.set(power);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new ShooterControl());
	}

	private class ShooterControl extends Command {
		protected void initialize() {
		}

		protected void execute() {
			setShooterAngle(Robot.oi.manipulatorJoystick.getRawAxis(1));// TODO:check
																		// axis
			if (Robot.oi.manipulatorJoystick.getRawButton(1)) {
				setShooterSpeed(-1);
				setTriggerSpeed(-.5);
			} else if (Robot.oi.manipulatorJoystick.getRawButton(3)) {
				setShooterSpeed(1);
				setTriggerSpeed(1);
			} else {
				setShooterSpeed(0);
			}

		}

		protected boolean isFinished() {
			return false;
		}

		protected void end() {
		}

		protected void interrupted() {
		}
	}

}