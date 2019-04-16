package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class OutputCargo extends Command {
  public OutputCargo() {
    requires(Robot.cargoIntake);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.cargoIntake.intakeSpin(RobotMap.outputSpeed);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
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
