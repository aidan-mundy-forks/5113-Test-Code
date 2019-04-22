package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import frc.robot.commands.TestAuton;
import frc.robot.commands.ToggleCargoPivot;
import frc.robot.commands.ToggleClimbPistons;
import frc.robot.commands.ToggleCompressor;
import frc.robot.commands.ToggleHatchPanelBar;
import frc.robot.commands.ToggleHatchPanelClamp;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  private final Joystick mainGamepad = new Joystick(0);
  private final Joystick secondaryGamepad = new Joystick(1);

  public OI() {
    final var compressorButton = new JoystickButton(mainGamepad, RobotMap.compressorButton);
    final JoystickButton pivotToggleButton = new JoystickButton(secondaryGamepad, RobotMap.pivotToggleButton);
    final JoystickButton clampButton = new JoystickButton(secondaryGamepad, RobotMap.clampButton);
    final JoystickButton barButton = new JoystickButton(secondaryGamepad, RobotMap.barButton);
    final JoystickButton climbToggleButton = new JoystickButton(mainGamepad, RobotMap.climbToggleButton);
    final POVButton autonButton = new POVButton(mainGamepad, 0);

    compressorButton.whenPressed(new ToggleCompressor());
    pivotToggleButton.whenPressed(new ToggleCargoPivot());
    clampButton.whenPressed(new ToggleHatchPanelClamp());
    barButton.whenPressed(new ToggleHatchPanelBar());
    climbToggleButton.whenPressed(new ToggleClimbPistons());
    autonButton.whileHeld(new TestAuton());
  }

  public double getDriveX() {
    return mainGamepad.getRawAxis(RobotMap.driveXStick);
  }

  public double getDriveY() {
    return mainGamepad.getRawAxis(RobotMap.driveYStick);
  }

  public double getDriveZ() {
    return mainGamepad.getRawAxis(RobotMap.driveZStick);
  }

  public boolean getCargoIntake() {
    return secondaryGamepad.getRawButton(RobotMap.cargoIntakeButton);
  }

  public boolean getCargoOutput() {
    return secondaryGamepad.getRawButton(RobotMap.cargoOutputButton);
  }

  public boolean getElevatorUp() {
    return mainGamepad.getRawButton(RobotMap.elevatorUpButton);
  }

  public boolean getElevatorDown() {
    return mainGamepad.getRawButton(RobotMap.elevatorDownButton);
  }

  public boolean getAutonForwardButton() {
    return mainGamepad.getRawButton(RobotMap.autonForwardButton);
  }
}
