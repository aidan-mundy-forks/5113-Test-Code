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
  public static final int frontLeftMotorCAN = 13;
  public static final int backLeftMotorCAN = 12;
  public static final int frontRightMotorCAN = 14;
  public static final int backRightMotorCAN = 15;

  // Drive Controls
  public static final int driveXStick = 0; // Forward/back
  public static final int driveYStick = 1; // Left/right strafe
  public static final int driveZStick = 4; // Left/right rotate

  // Cargo Intake
  public static final int intakeMotorCAN = 7;
  public static final int pivotSolenoid0 = 6;
  public static final int pivotSolenoid1 = 7;
  public static final int cargoIntakeButton = 1;
  public static final int cargoOutputButton = 2;
  public static final int pivotToggleButton = 6;
  public static final double intakeSpeed = 0.4;
  public static final double outputSpeed = 0.6;

  // Miscellaneous Controls
  public static final int compressorButton = 7;
}
