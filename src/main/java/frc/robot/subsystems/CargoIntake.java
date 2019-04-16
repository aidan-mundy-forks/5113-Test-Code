package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.CargoControl;

public class CargoIntake extends Subsystem {
  private WPI_TalonSRX intakeMotor = new WPI_TalonSRX(RobotMap.intakeMotorCAN);
  private DoubleSolenoid pivot = new DoubleSolenoid(RobotMap.pivotSolenoid0, RobotMap.pivotSolenoid1);

  public CargoIntake() {
    intakeMotor.setInverted(true); // Set as inverted because it spins the wrong direction.
    pivot.set(Value.kForward); // Initialized as kForward because that is its starting configuration.
  }

  public void intakeSpin(double speed) {
    intakeMotor.set(speed);
  }

  public void intakeOff() {
    intakeMotor.set(0);
  }

  public void togglePivot() {
    if (pivot.get() == Value.kForward) {
      pivot.set(Value.kReverse);
    } else {
      pivot.set(Value.kForward);
    }
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new CargoControl());
  }
}
