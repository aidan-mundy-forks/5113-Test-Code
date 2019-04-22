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
      Robot.driveBase.driveCartesian(autonPStrafeSpeed(), autonPDriveSpeed(), autonPRotateSpeed());
      System.out.println("Trying to rotate " + autonPRotateSpeed());
      System.out.println("Trying to strafe " + autonPStrafeSpeed());
    }

  }

  private double autonPStrafeSpeed() {
    double threshold = .92;
    double target0Area;
    double target1Area;

    /*
     * This is necessary because targets arent always located in a logical sort in the array
     * and it was easier to to this than to actually follow heirarchy rules. ¯\_(ツ)_/¯
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

    double maxSpeed = .5;
    double minSpeed = .18;

    double speedDiff = maxSpeed - minSpeed;

    return Math.abs(((ratio - 0.35) / .65) * speedDiff) + minSpeed;
  }

  private double autonPDriveSpeed() {
    double driveSpeed = -.25;

    if (Robot.oi.getAutonForwardButton()) {
      return driveSpeed;
    } else {
      return 0;
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
