package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class CargoControl extends Command {
  public CargoControl() {
    requires(Robot.cargoIntake);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.oi.getCargoIntake()) {
      Robot.cargoIntake.intakeSpin(-RobotMap.intakeSpeed);
    } else if (Robot.oi.getCargoOutput()) {
      Robot.cargoIntake.intakeSpin(RobotMap.outputSpeed);
    } else {
      Robot.cargoIntake.intakeOff();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.cargoIntake.intakeOff();
  }
}
