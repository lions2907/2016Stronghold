package org.usfirst.frc.team2907.robot;

import org.usfirst.frc.team2907.robot.commands.Shift;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	public Joystick driverJoystick;
	public Joystick manipulatorJoystick;
	public JoystickButton square;
	public OI() {
		driverJoystick = new Joystick(1);
		manipulatorJoystick = new Joystick(2);
		square = new JoystickButton(driverJoystick, 1);
		square.whenPressed(new Shift());
		
	}
}
