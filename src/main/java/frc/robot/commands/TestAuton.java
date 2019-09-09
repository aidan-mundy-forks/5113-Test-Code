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
      double strafeSpeed = autonPStrafeSpeed();
      double rotateSpeed = autonPRotateSpeed();
      Robot.driveBase.driveCartesian(strafeSpeed, autonPDriveSpeed(strafeSpeed, rotateSpeed), rotateSpeed);
    }

  }

  private double autonPStrafeSpeed() {
    double threshold = .93;
    double target0Area;
    double target1Area;

    /*
     * This is necessary because targets arent always located in a logical sort in the array
     * and it was easier do this than to actually follow heirarchy rules. ¯\_(ツ)_/¯
     */
    if (Robot.network.getContourInfo(DataType.x, 0) < Robot.network.getContourInfo(DataType.x, 1)) {
      target0Area = Robot.network.getContourInfo(DataType.area, 0);
      target1Area = Robot.network.getContourInfo(DataType.area, 1);
    } else {
      target0Area = Robot.network.getContourInfo(DataType.area, 1);
      target1Area = Robot.network.getContourInfo(DataType.area, 0);
    }

    double ratioTarget1Bigger = target0Area / target1Area;
    double ratioTarget0Bigger = target1Area / target0Area;

    if (ratioTarget0Bigger > threshold && ratioTarget1Bigger > threshold) {
      return 0;
    } else if (target0Area < target1Area) {
      return -autonPStrafeSpeedMath(ratioTarget1Bigger);
    } else {
      return autonPStrafeSpeedMath(ratioTarget0Bigger);
    }
  }

  private double autonPStrafeSpeedMath(double ratio) {
    double threshold = .35; // NOTE: This is not the accuracy threshold, this is the RATIO threshold.
    double maxSpeed = .6;
    double minSpeed = .25;

    double speedDiff = maxSpeed - minSpeed;
    double thresholdedTop = 1 - threshold;

    return ((1 - (Math.abs(ratio - threshold) / thresholdedTop)) * speedDiff) + minSpeed;
  }

  private double autonPDriveSpeed(double strafeSpeed, double rotateSpeed) {
    double driveSpeed = -.25;
    double movingDriveSpeed = -.05;
    double rotatingThreshold = .1;
    double strafingThreshold = .4;

    if (Robot.oi.getAutonForwardButton()) {
      if (rotateSpeed > rotatingThreshold && strafeSpeed > strafingThreshold) {
        return movingDriveSpeed;
      } else {
        return driveSpeed;
      }
    } else {
      return 0;
    }
  }

  private double autonPRotateSpeed() {
    int threshold = 13;
    double maxSpeed = .6;
    double minSpeed = .12;
    double minSpeedWhileDriving = .07;

    if (Robot.oi.getAutonForwardButton()) {
      minSpeed = minSpeedWhileDriving;
    }

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
