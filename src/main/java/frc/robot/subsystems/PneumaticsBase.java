package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PneumaticsBase extends Subsystem {

  private final Compressor compressor = new Compressor();

  public PneumaticsBase() {
    enableCompressor();
  }

  public void enableCompressor() {
    compressor.setClosedLoopControl(true);
  }

  public void disableCompressor() {
    compressor.setClosedLoopControl(true);
  }

  public void toggleCompressor() {
    compressor.setClosedLoopControl(!compressor.getClosedLoopControl());
  }

  @Override
  public void initDefaultCommand() {
    // This subsystem does not need a default command.
  }
}
