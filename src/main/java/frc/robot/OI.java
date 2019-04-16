package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  Joystick mainGamepad = new Joystick(0);
  Joystick secondaryGamepad = new Joystick(1);

  public double getDriveX() {
    return mainGamepad.getRawAxis(RobotMap.driveXStick);
  }

  public double getDriveY() {
    return mainGamepad.getRawAxis(RobotMap.driveYStick);
  }

  public double getDriveZ() {
    return mainGamepad.getRawAxis(RobotMap.driveZStick);
  }
}
