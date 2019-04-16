package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around. For example to map the left and right motors, you could
 * define the following variables to use with your drivetrain subsystem. public
 * static int leftMotor = 1; public static int rightMotor = 2;
 * 
 * If you are using multiple modules, make sure to define both the port number
 * and the module. For example you with a rangefinder: public static int
 * rangefinderPort = 1; public static int rangefinderModule = 1;
 */
public class RobotMap {
  // Drive Motors
  public static int frontLeftMotorCAN = 13;
  public static int backLeftMotorCAN = 12;
  public static int frontRightMotorCAN = 14;
  public static int backRightMotorCAN = 15;

  // Drive Controls
  public static int driveXStick = 0; // Forward/back
  public static int driveYStick = 1; // Left/right strafe
  public static int driveZStick = 4; // Left/right rotate
}
