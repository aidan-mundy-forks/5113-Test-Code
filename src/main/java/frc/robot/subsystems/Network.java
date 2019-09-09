package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Network extends Subsystem {
  public static NetworkTableInstance nt = NetworkTableInstance.getDefault();

  public enum DataType {
    x, y, width, height, area, solidity, ratio
  }

  public double getContourInfo(DataType dataType, int index) {
    if (index < 0 || index > 1) {
      return 0.0;
    } else {
      return nt.getTable("/visionProcessing/targetInfo/contoursReport").getEntry(dataType.name())
          .getDoubleArray(new double[] { 0.0, 0.0 })[index]; // While the "new" operation without a corresponding
                                                             // delete() operation would be considered bad practice in
                                                             // many scenarios and languages, Java has garbage
                                                             // collection so this isnt really a problem.
    }
  }

  public Boolean getErrorState() {
    return nt.getTable("/visionProcessing").getEntry("error").getBoolean(true);
  }

  public String getErrorMessage() {
    return nt.getTable("/visionProcessing").getEntry("errorMessage").getString("Cant find the message!");
  }

  @Override
  public void initDefaultCommand() {
    // This subsystem does not need a default command.
  }
}
