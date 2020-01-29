package frc.robot.subsystems;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class NetworktablesSubSystem extends SubsystemBase {
 
  private NetworkTable networkTable;
  private double angle;
  private double distance;

  private static NetworktablesSubSystem instance;

  private NetworktablesSubSystem() {
    // initiate variables
    angle = 0;
    distance = 0;
    networkTable = NetworkTableInstance.getDefault().getTable("tb");

    // listener creation
    networkTable.addEntryListener("angle", (table, key, entry, value, flags) -> {
      angle = (double) value.getValue();
      SmartDashboard.putNumber("angle", angle);
      //System.out.print(angle);
    }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
    
    networkTable.addEntryListener("distance", (table, key, entry, value, flags) -> {
        distance = (double) value.getValue();
        SmartDashboard.putNumber("distance", distance);
        //System.out.print(distance);
      }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

  }

  /**
   * Get the distance from the target.
   * @return distance from target
   */
  public double get_distance(){
    return distance;
  }

  /**
   * Get the angle from the target.
   * @return angle from target
   */
  public double get_angle(){
      return angle;
  }

  /**
   * Singleton function, returns the ONLY instance of the class.
   * @return instance of the class.
   */
  public static NetworktablesSubSystem getInstance() {
    if (instance == null)
      instance = new NetworktablesSubSystem();
    return instance;
  }

}
