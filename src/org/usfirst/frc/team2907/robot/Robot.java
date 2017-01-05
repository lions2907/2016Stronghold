package org.usfirst.frc.team2907.robot;

import org.usfirst.frc.team2907.robot.commands.LowBarAuto;
import org.usfirst.frc.team2907.robot.commands.NoAuto;
import org.usfirst.frc.team2907.robot.commands.NonLowBarAuto;
import org.usfirst.frc.team2907.robot.subsystems.Collector;
import org.usfirst.frc.team2907.robot.subsystems.DriveTrain;
import org.usfirst.frc.team2907.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	Command autonomousCommand;
	public static OI oi;

	public static DriveTrain drivetrain;
	public static Collector collector;
	public static Shooter shooter;

	public SendableChooser autoChooser;
	public SendableChooser autonomousDirectionChooser;

	public void robotInit() {
		// Initialize the subsystems
		drivetrain = DriveTrain.getInstance();
		collector = Collector.getInstance();
		shooter = Shooter.getInstance();
		SmartDashboard.putData(drivetrain);
		SmartDashboard.putData(collector);
		SmartDashboard.putData(shooter);
		oi = new OI();

		autoChooser = new SendableChooser();
		autoChooser.addDefault("Non Low Bar", new NonLowBarAuto());
		autoChooser.addObject("Low Bar", new LowBarAuto());
		autoChooser.addObject("No Auto", new NoAuto());
		SmartDashboard.putData("Auto Mode", autoChooser);
	}

	public void autonomousInit() {
		autonomousCommand = (Command) autoChooser.getSelected();
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	// This function is called periodically during autonomous
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	public void testPeriodic() {
		LiveWindow.run();
	}

	public void disabledInit() {
	}

	// This function is called periodically while disabled
	public void disabledPeriodic() {
	}
}
