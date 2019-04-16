package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class HatchPanelIntake extends Subsystem {
  private Solenoid clamp = new Solenoid(RobotMap.clampSolenoid);
  private Solenoid bar = new Solenoid(RobotMap.barSolenoid);

  public HatchPanelIntake() {
    clamp.set(false);
    bar.set(false);
  }

  public void toggleClamp() {
    clamp.set(!clamp.get());
  }

  public void toggleBar() {
    bar.set(!bar.get());
  }

  @Override
  public void initDefaultCommand() {
    // This subsystem does not need a default command.
  }
}
