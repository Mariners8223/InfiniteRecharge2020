package frc.robot.subsystems;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class NetworktablesSubSystem extends SubsystemBase {
  // NetworkTables Setup
  private NetworkTableInstance inst;
  private NetworkTable networkTable;

  // Singletone Instance
  private static NetworktablesSubSystem instance;

  // 2 Camera Setup
  public VideoSink camsServer = CameraServer.getInstance().getServer();
  UsbCamera camera0 = CameraServer.getInstance().startAutomaticCapture(0);
  UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(1);
  private boolean corrent_cam = true;

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

  /** 
   * get the current camera object
   * @return camera object
   */
  public UsbCamera get_corrent_cam() {
    if(corrent_cam) {
      return camera1;
    }
	  return camera0;
  }

  /**
   * switch the camera boolean
   */
  public void switch_camera() {
	  corrent_cam = !corrent_cam;
  }
}