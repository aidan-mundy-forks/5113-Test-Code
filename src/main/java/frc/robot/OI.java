package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.IntakeCargo;
import frc.robot.commands.OutputCargo;
import frc.robot.commands.ToggleCargoPivot;
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
    final JoystickButton compressorButton = new JoystickButton(mainGamepad, RobotMap.compressorButton);
    final JoystickButton cargoIntakeButton = new JoystickButton(secondaryGamepad, RobotMap.cargoIntakeButton);
    final JoystickButton cargoOutputButton = new JoystickButton(secondaryGamepad, RobotMap.cargoOutputButton);
    final JoystickButton pivotToggleButton = new JoystickButton(secondaryGamepad, RobotMap.pivotToggleButton);
    final JoystickButton clampButton = new JoystickButton(secondaryGamepad, RobotMap.clampButton);
    final JoystickButton barButton = new JoystickButton(secondaryGamepad, RobotMap.barButton);

    compressorButton.whenPressed(new ToggleCompressor());
    cargoIntakeButton.whileHeld(new IntakeCargo());
    cargoOutputButton.whileHeld(new OutputCargo());
    pivotToggleButton.whenPressed(new ToggleCargoPivot());
    clampButton.whenPressed(new ToggleHatchPanelClamp());
    barButton.whenPressed(new ToggleHatchPanelBar());
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
}
