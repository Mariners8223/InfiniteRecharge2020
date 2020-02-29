package frc.robot.subsystems;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class NetworktablesSubSystem extends SubsystemBase {
  SerialPort rpi;
  // NetworkTables Setup
  private NetworkTable networkTable;

  // Singletone Instance
  private static NetworktablesSubSystem instance;


  private NetworktablesSubSystem() {
    rpi = new SerialPort(9600, SerialPort.Port.kOnboard, 8);
  }

  /**
   * Get the angle from the target.
   * @return angle from target
   */
  public double get_angle(){
    return Double.parseDouble(rpi.readString());
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