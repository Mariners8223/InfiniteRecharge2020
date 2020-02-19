package frc.robot.subsystems;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTablesJNI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class NetworktablesSubSystem extends SubsystemBase {
  private NetworkTableInstance inst;
  private NetworkTable networkTable;
  private double angle;
  private double distance;

  private static NetworktablesSubSystem instance = new NetworktablesSubSystem();

  private NetworktablesSubSystem() {
    
    // initiate variables
    angle = 0;
    distance = 0;
    inst = NetworkTableInstance.getDefault();
    inst.startClientTeam(8223);
    inst.startDSClient(); 
    System.out.println(inst.isConnected());
    networkTable = inst.getTable("SmartDashboard");


    System.out.println("Start list");
    System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&77");

    // listener creation
    // networkTable.addEntryListener("ang", (table, key, entry, value, flags) -> {
    //   System.out.println("ang-called");
    //   angle = (double) value.getValue();
    //   SmartDashboard.putNumber("angle", angle);
    //   System.out.print("from nss:" + angle);
    // }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);
    
    // networkTable.addEntryListener("vel", (table, key, entry, value, flags) -> {
    //   System.out.println("vel-called");
    //     distance = (double) value.getValue();
    //     SmartDashboard.putNumber("velocity", distance);
    //     //System.out.print(distance);
    //   }, EntryListenerFlags.kNew | EntryListenerFlags.kUpdate);

    //   ang = networkTable.getEntry("ang");
  }

  /**
   * Get the distance from the target.
   * @return distance from target
   */
  public double get_distance(){
    return networkTable.getEntry("dis").getDouble(0);
    // return distance;
  }

  /**
   * Get the angle from the target.
   * @return angle from target
   */
  public double get_angle(){
    return networkTable.getEntry("ang").getDouble(0);
      // return ang.getDouble(0);
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
