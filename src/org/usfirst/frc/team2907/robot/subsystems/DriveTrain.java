package org.usfirst.frc.team2907.robot.subsystems;

import org.usfirst.frc.team2907.robot.commands.DelayedCallback;
import org.usfirst.frc.team2907.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The DriveTrain subsystem controls the robot's chassis and reads in
 * information about it's speed and position.
 */
public class DriveTrain extends Subsystem {
	// Subsystem devices
	private CANTalon left1, left2, left3, right1, right2, right3;
	private DoubleSolenoid shifter;
	private static DriveTrain instance = new DriveTrain();

	public static DriveTrain getInstance() {
		return instance;
	}

	private DriveTrain() {
		// Configure drive motors
		left1 = new CANTalon(1);
		left2 = new CANTalon(2);
		left3 = new CANTalon(3);
		right1 = new CANTalon(4);
		right2 = new CANTalon(5);
		right3 = new CANTalon(6);
		right1.setInverted(true);
		right2.setInverted(true);
		right3.setInverted(true);
		shifter = new DoubleSolenoid(4, 5);
		// Configure the RobotDrive to reflect the fact that all our motors are
		// wired backwards and our drivers sensitivity preferences.
	}

	/**
	 * When other commands aren't using the drivetrain, allow arcade drive with
	 * the joystick.
	 */
	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	public boolean isHighGear;

	public void shift(boolean highGear) {
		if (highGear) {
			shifter.set(DoubleSolenoid.Value.kReverse);
			isHighGear = true;
		} else {
			shifter.set(DoubleSolenoid.Value.kForward);
			isHighGear = false;
		}
		Scheduler.getInstance().add(new DelayedCallback(0.25) {
			public void onCallback() {
				shifter.set(DoubleSolenoid.Value.kOff);
			}
		});
	}

	/**
	 * @param joy
	 *            PS3 style joystick to use as the input for tank drive.
	 */
	public void arcadeDrive(Joystick joy) {
		arcadeDrive(Math.pow(Math.abs(joy.getY()), 0.5) * joy.getY(), joy.getTwist());
	}

	/**
	 * @param leftAxis
	 *            Left sides value
	 * @param rightAxis
	 *            Right sides value
	 */
	public void arcadeDrive(double move, double rotate) {
		double leftSpeed = move + rotate;
		double rightSpeed = move - rotate;
		left1.set(leftSpeed);
		left2.set(leftSpeed);
		left3.set(leftSpeed);
		right1.set(rightSpeed);
		right2.set(rightSpeed);
		right3.set(rightSpeed);
	}

	/**
	 * Stop the drivetrain from moving.
	 */
	public void stop() {
		arcadeDrive(0, 0);
	}
}
