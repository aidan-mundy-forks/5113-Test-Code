package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.SendToSmartDashboard;
import frc.robot.subsystems.CargoIntake;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.HatchPanelIntake;
import frc.robot.subsystems.Network;
import frc.robot.subsystems.PneumaticsBase;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation.
 */
public class Robot extends TimedRobot {
  public static DriveBase driveBase;
  public static PneumaticsBase pneumaticsBase;
  public static CargoIntake cargoIntake;
  public static HatchPanelIntake hatchPanelIntake;
  public static Climber climber;
  public static OI oi;
  public static Network network;

  private SendToSmartDashboard sendToSmartDashboard;

  Command autonomousCommand;
  SendableChooser<Command> chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    driveBase = new DriveBase();
    pneumaticsBase = new PneumaticsBase();
    cargoIntake = new CargoIntake();
    hatchPanelIntake = new HatchPanelIntake();
    climber = new Climber();
    oi = new OI();
    network = new Network();

    sendToSmartDashboard = new SendToSmartDashboard();
    sendToSmartDashboard.start();

    // chooser.setDefaultOption("Default Auto", new ExampleCommand()); //
    // chooser.addOption("My Auto", new MyAutoCommand());
    // SmartDashboard.putData("Auto mode", chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   */
  @Override
  public void robotPeriodic() {
    if (!sendToSmartDashboard.isRunning()) {
      sendToSmartDashboard.start();
    }
  }

  /*
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Autonomous mode. This
   * autonomous (along with the chooser code above) shows how to select between
   * different autonomous modes using the dashboard. The sendable chooser code
   * works with the Java SmartDashboard.
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    autonomousCommand = chooser.getSelected();

    // Start the autonomous command, and ignore if there isnt one.
    if (autonomousCommand != null) {
      autonomousCommand.start();
    }
  }

  // This function is called periodically during autonomous.
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when teleop starts running.
    // If you want the autonomous to continue until interrupted by another command,
    // remove this line or comment it out.
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
  }

  // This function is called periodically during operator control.
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  // This function is called once each time the robot enters Test mode.
  @Override
  public void testInit() {
  }

  // This function is called periodically during test mode.
  @Override
  public void testPeriodic() {
  }
}
