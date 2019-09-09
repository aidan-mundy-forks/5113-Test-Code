package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TeleopDriveNormal extends Command {
  public TeleopDriveNormal() {
    requires(Robot.driveBase);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveBase.stop();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driveBase.driveCartesian(Robot.oi.getDriveX(), Robot.oi.getDriveY(), Robot.oi.getDriveZ());
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
    Robot.driveBase.stop();
  }
}
