package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.RobotMap;
import frc.robot.commands.TeleopDriveNormal;
import edu.wpi.first.wpilibj.SPI;

public class DriveBase extends Subsystem {
  private WPI_TalonSRX frontLeftWheel = new WPI_TalonSRX(RobotMap.frontLeftMotorCAN);
  private WPI_TalonSRX backLeftWheel = new WPI_TalonSRX(RobotMap.backLeftMotorCAN);
  private WPI_TalonSRX frontRightWheel = new WPI_TalonSRX(RobotMap.frontRightMotorCAN);
  private WPI_TalonSRX backRightWheel = new WPI_TalonSRX(RobotMap.backRightMotorCAN);
  private MecanumDrive mecDrive = new MecanumDrive(frontRightWheel, frontLeftWheel, backRightWheel, backLeftWheel);

  private AHRS navx = new AHRS(SPI.Port.kMXP);

  public DriveBase() {
    frontRightWheel.setNeutralMode(NeutralMode.Brake);
    frontLeftWheel.setNeutralMode(NeutralMode.Brake);
    backLeftWheel.setNeutralMode(NeutralMode.Brake);
    backRightWheel.setNeutralMode(NeutralMode.Brake);

    // Set the current limits for each wheel
    frontRightWheel.configPeakCurrentLimit(80);
    frontRightWheel.configPeakCurrentDuration(500);
    frontRightWheel.configContinuousCurrentLimit(60);
    frontRightWheel.enableCurrentLimit(true);

    frontLeftWheel.configPeakCurrentLimit(80);
    frontLeftWheel.configPeakCurrentDuration(500);
    frontLeftWheel.configContinuousCurrentLimit(60);
    frontLeftWheel.enableCurrentLimit(true);

    backRightWheel.configPeakCurrentLimit(80);
    backRightWheel.configPeakCurrentDuration(500);
    backRightWheel.configContinuousCurrentLimit(60);
    backRightWheel.enableCurrentLimit(true);

    backLeftWheel.configPeakCurrentLimit(80);
    backLeftWheel.configPeakCurrentDuration(500);
    backLeftWheel.configContinuousCurrentLimit(60);
    backLeftWheel.enableCurrentLimit(true);

    // Set encoders for each wheel
    frontRightWheel.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    frontLeftWheel.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    backRightWheel.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    backLeftWheel.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
  }

  public void driveCartesian(double xPower, double yPower, double rotation) {
    mecDrive.driveCartesian(yPower, xPower, rotation);
  }

  public void driveCartesianBackward(double xPower, double yPower, double rotation) {
    mecDrive.driveCartesian((-1 * (xPower)), (-1 * (yPower)), rotation);
  }

  public void stop() {
    mecDrive.driveCartesian(0, 0, 0);
  }

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new TeleopDriveNormal());
  }
}
