package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.subsystems.Network.DataType;

public class SendToSmartDashboard extends Command {
  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    SmartDashboard.putData(Robot.driveBase);
    SmartDashboard.putData(Robot.pneumaticsBase);
    SmartDashboard.putData(Robot.cargoIntake);
    SmartDashboard.putData(Robot.hatchPanelIntake);
    SmartDashboard.putData(Robot.climber);
    SmartDashboard.putNumber("x Joystick", Robot.oi.getDriveX());
    SmartDashboard.putNumber("y Joystick", Robot.oi.getDriveY());
    SmartDashboard.putNumber("z Joystick", Robot.oi.getDriveZ());
    if (Robot.network.getContourInfo(DataType.area, 0) < Robot.network.getContourInfo(DataType.area, 1)) {
      SmartDashboard.putNumber("ratio",
          Robot.network.getContourInfo(DataType.area, 0) / Robot.network.getContourInfo(DataType.area, 1));
    } else {
      SmartDashboard.putNumber("ratio",
          Robot.network.getContourInfo(DataType.area, 1) / Robot.network.getContourInfo(DataType.area, 0));
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // This runs if the command is unexpectedly interrupted.
  @Override
  protected void interrupted() {
    System.out.println("SendToSmartDashBoard was interrupted.");
  }
}
