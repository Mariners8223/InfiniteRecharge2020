package frc.robot.subsystems;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class NetworktablesSubSystem extends SubsystemBase {
  private NetworkTableInstance inst;
  private NetworkTable networkTable;

  private static NetworktablesSubSystem instance = new NetworktablesSubSystem();

  public VideoSink camsServer = CameraServer.getInstance().getServer();
  UsbCamera camera0 = CameraServer.getInstance().startAutomaticCapture(0);
  UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(1);
  int corrent_cam = 0;

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

  public UsbCamera get_corrent_cam() {
    if(corrent_cam == 1) {
      return camera1;
    }
	  return camera0;
  }

  public void next_cam() {
	  corrent_cam = (corrent_cam + 1) % 2;
  }
}