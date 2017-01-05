package org.usfirst.frc.team2907.robot.subsystems;

import org.usfirst.frc.team2907.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The Collector subsystem has one motor for the rollers, a limit switch for
 * ball detection, a piston for opening and closing the claw, and a reed switch
 * to check if the piston is open.
 */
public class Collector extends Subsystem {
	// Subsystem devices
	private SpeedController intake1;
	private SpeedController intake2;
	private SpeedController arm1;
	private SpeedController arm2;
	private static Collector instance = new Collector();

	public static Collector getInstance() {
		return instance;
	}

	private Collector() {
		// Configure devices

		intake1 = new CANTalon(7); // define positive as "out"
		intake2 = new CANTalon(8);
		intake2.setInverted(true);
		arm1 = new CANTalon(9);// up is positive
		arm2 = new CANTalon(11);
		arm1.setInverted(true);
	}

	public void setSpeed(double speed) {
		intake1.set(speed);
		intake2.set(speed);
	}

	public void setArmPower(double power) {
		arm1.set(power);
		arm2.set(power);
	}

	public void stop() {
		setSpeed(0.0);
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new CollectorControl());
	}

	private class CollectorControl extends Command {
		protected void initialize() {
		}

		protected void execute() {
			if (Robot.oi.driverJoystick.getRawButton(6) || Robot.oi.manipulatorJoystick.getPOV() == 0) {
				Collector.getInstance().setArmPower(0.6);
			} else if (Robot.oi.driverJoystick.getRawButton(5) || Robot.oi.manipulatorJoystick.getPOV() == 180) {
				Collector.getInstance().setArmPower(-0.4);
			} else {
				Collector.getInstance().setArmPower(0.0);
			}
			if (Robot.oi.manipulatorJoystick.getRawButton(1) || Robot.oi.manipulatorJoystick.getRawButton(9)) {
				setSpeed(-1);
			} else if (Robot.oi.manipulatorJoystick.getRawButton(10)) {
				setSpeed(1);
			} else {
				setSpeed(0);
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
