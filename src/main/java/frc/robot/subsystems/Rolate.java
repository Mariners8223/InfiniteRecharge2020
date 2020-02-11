package frc.robot.subsystems;

import edu.wpi.first.networktables.EntryListenerFlags;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.Constants;

public class Rolate extends SubsystemBase {
  public final Spark spinner;

  public final double SPINNER_SPEED = 0.3;
  private Compressor compressor;
  private DoubleSolenoid solenoid;

  private static Rolate instance;

  private Rolate() {
    compressor = new Compressor();
    compressor.stop();
    solenoid = new DoubleSolenoid(Constants.ROLATE_SOLONOID_A, Constants.ROLATE_SOLONOID_B);

    spinner = new Spark(Constants.SPINNER_MOTOR);

  }

  /**
   * Singleton function, returns the ONLY instance of the class.
   * @return instance of the class.
   */
  public static Rolate getInstance() {
    if (instance == null)
      instance = new Rolate();
    return instance;
  }

 
  /**
   * Set the solenoid to forword postion ("DoubleSolenoid.Value.kForward")
   */
  public void rolate_move_forword(){
    solenoid.set(DoubleSolenoid.Value.kForward);
  }

  /**
   * Stop the solenoid ("DoubleSolenoid.Value.kOff")
   */
  public void rolate_stop(){
    solenoid.set(DoubleSolenoid.Value.kOff);
  }

  /**
   * Set the solenoid to reverse postion ("DoubleSolenoid.Value.kReverse")
   */
  public void rolate_move_reverse(){
    solenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void spinner_set_speed(double speed){
    spinner.set(speed);
  }


}
