package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Network.DataType;

public class TestAuton extends Command {
  public TestAuton() {
    requires(Robot.driveBase);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.network.getErrorState()) {
      Robot.driveBase.stop();
      System.out.println("AutonErrorStateTriggered");
    } else {
      Robot.driveBase.driveCartesian(0, 0, autonPRotateSpeed());
      System.out.println("Trying to drive " + autonPRotateSpeed());
    }

  }

  private double autonPRotateSpeed() {
    int threshold = 30;
    if (Math.abs((Robot.network.getContourInfo(DataType.x, 0) + Robot.network.getContourInfo(DataType.x, 1)) / 2 - 480) < threshold) {
      return 0;
    } else if ((Robot.network.getContourInfo(DataType.x, 0) + Robot.network.getContourInfo(DataType.x, 1)) / 2 - 480 > 0) {
      return ((((Robot.network.getContourInfo(DataType.x, 0) + Robot.network.getContourInfo(DataType.x, 1)) / 2) - 480) / 400) * .41 + .09;
    } else {
      return ((((Robot.network.getContourInfo(DataType.x, 0) + Robot.network.getContourInfo(DataType.x, 1)) / 2) - 480) / 400) * .41 - .09;
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
    Robot.driveBase.stop();
  }
}
