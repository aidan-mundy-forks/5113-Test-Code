package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.ElevatorControl;

public class Climber extends Subsystem {
  private WPI_TalonSRX elevatorMotor = new WPI_TalonSRX(RobotMap.elevatorMotorCAN);
  private DoubleSolenoid climb = new DoubleSolenoid(RobotMap.climbSolenoid0, RobotMap.climbSolenoid1);

  public Climber() {
    pivot.set(Value.kReverse); // Initialized as kReverse because that is its starting configuration.
  }

  public void elevatorSpeed(double speed) {
    elevatorMotor.set(speed);
  }

  public void elevatorOff() {
    elevatorMotor.set(0);
  }

  public void toggleClimb() {
    if (climb.get() == Value.kReverse) {
      climb.set(Value.kForward);
    } else {
      climb.set(Value.kReverse);
    }
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ElevatorControl());
  }
}
