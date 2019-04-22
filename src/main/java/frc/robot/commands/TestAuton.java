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
      System.out.println(Robot.network.getErrorMessage());
    } else {
      Robot.driveBase.driveCartesian(autonPStrafeSpeed(), Robot.oi.getDriveY(), autonPRotateSpeed());
      System.out.println("Trying to drive " + autonPRotateSpeed());
    }

  }

  private double autonPRotateSpeed() {
    int threshold = 10;
    double maxSpeed = .5;
    double minSpeed = .12;

    double speedDiff = maxSpeed - minSpeed;
    double actualPosition = (Robot.network.getContourInfo(DataType.x, 0) + Robot.network.getContourInfo(DataType.x, 1)) / 2 - 480;
    double scaledPosition = Math.pow((actualPosition) / 480, 2) * speedDiff;
    if (Math.abs(actualPosition) < threshold) {
      return 0;
    } else if (actualPosition > 0) {
      return scaledPosition + minSpeed;
    } else {
      return -scaledPosition - minSpeed;
    }
  }

  private double autonPStrafeSpeed() {
    double threshold = .98;

    double ratioTarget1Bigger = Robot.network.getContourInfo(DataType.area, 0) / Robot.network.getContourInfo(DataType.area, 1);
    double ratioTarget0Bigger = Robot.network.getContourInfo(DataType.area, 1) / Robot.network.getContourInfo(DataType.area, 0);
    if (ratioTarget0Bigger > threshold && ratioTarget1Bigger > threshold) {
      return 0;
    } else if (Robot.network.getContourInfo(DataType.area, 0) < Robot.network.getContourInfo(DataType.area, 1)) {
      return -autonPStrafeSpeedMath(ratioTarget1Bigger);
    } else {
      return autonPStrafeSpeedMath(ratioTarget0Bigger);
    }
  }

  private double autonPStrafeSpeedMath(double ratio) {

    double maxSpeed = .5;
    double minSpeed = .25;

    double speedDiff = maxSpeed - minSpeed;

    return (((ratio - 0.75) / 0.75) * speedDiff) + minSpeed;
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
