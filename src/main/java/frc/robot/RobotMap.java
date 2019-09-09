package frc.robot;

/**
 * The RobotMap is a list of numeric or boolean variables used in any class. Any
 * port numbers, speed modifiers, axis or button ids, or anything else user
 * configurable should be always placed here. Constants should also typically be
 * placed in RobotMap in most situations where they are important to know and/or
 * shared between multiple classes, such as encoder counts.
 * This provides flexibility changing wiring, makes checking the wiring easier,
 * makes changing settings quicker and easier, and significantly reduces the
 * number of magic numbers floating around.
 * For example to map the left and right motors, you could define the following
 * variables to use with your drivetrain subsystem. 
 * public static int leftMotor = 1;
 * public static int rightMotor = 2;
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

  // Hatch Panel Intake
  public static final int clampSolenoid = 4;
  public static final int barSolenoid = 5;
  public static final int clampButton = 3;
  public static final int barButton = 4;

  // Climber
  public static final int elevatorMotorCAN = 11;
  public static final int climbSolenoid0 = 0;
  public static final int climbSolenoid1 = 1;
  public static final int elevatorUpButton = 6;
  public static final int elevatorDownButton = 5;
  public static final int climbToggleButton = 3;
  public static final double elevatorSpeed = 1;

  // Miscellaneous Controls
  public static final int autonForwardButton = 2;
  public static final int compressorButton = 7;
}
