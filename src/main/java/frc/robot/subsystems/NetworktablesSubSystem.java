package frc.robot.subsystems;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class NetworktablesSubSystem extends SubsystemBase {
  // NetworkTables Setup
  private NetworkTableInstance inst;
  private NetworkTable networkTable;

  // Singletone Instance
  private static NetworktablesSubSystem instance;

  private NetworktablesSubSystem() {
    // initiate variables
    inst = NetworkTableInstance.getDefault();
    inst.startClientTeam(8223);
    inst.startDSClient(); 

    // System.out.println(inst.isConnected());
    networkTable = inst.getTable("SmartDashboard");
  }

  /**
   * Get the distance from the target.
   * @return distance from target
   */
  public double get_velocity(){
    return networkTable.getEntry("vel").getDouble(0);
  }
  /**
   * Get the X - Error from the target.
   * @return X - Error from target
   */
  public double get_tx(){
    return networkTable.getEntry("tx").getDouble(0);
  }
  /**
   * Get the distance from the target.
   * @return distance from target
   */
  public double get_distance(){
    NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    double ty = table.getEntry("ty").getDouble(0);
    double static_angle = Constants.CAMERA_ANGLE;
    double hight = Constants.HIGHT_DIFFERENCE;
    double ty_rad = ty / (180/Math.PI);
    double static_angle_rad = static_angle / (180/Math.PI);
    double distance = hight/(Math.tan((static_angle + ty)));
    System.out.println(distance);
    SmartDashboard.putNumber("distance danko", distance);
    return distance;
    // SmartDashboard.putNumber("distance danko", distance);
    // return 100;

    
  }


  /**
   * Get the angle from the target.
   * @return angle from target
   */
  public double get_angle(){
    return networkTable.getEntry("ang").getDouble(0);
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