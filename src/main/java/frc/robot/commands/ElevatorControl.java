package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ElevatorControl extends Command {
  public ElevatorControl() {
    requires(Robot.climber);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.oi.getElevatorUp()) {
      Robot.climber.elevatorSpeed(RobotMap.elevatorSpeed);
    } else if (Robot.oi.getElevatorDown()) {
      Robot.climber.elevatorSpeed(-RobotMap.elevatorSpeed);
    } else {
      Robot.climber.elevatorOff();
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
    Robot.climber.elevatorOff();
  }
}
