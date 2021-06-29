package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Rolate extends SubsystemBase {

  // Motor, Compressor and Solenoid Setup
  private Compressor compressor;
  // private DoubleSolenoid solenoid;
  // public final TalonSRX spinner;

  // Speed Constances
  public final double SPINNER_SPEED = 0.6;

  // Command Toggle
  public boolean rolate_toggle;

  // Singletone Instance
  private static Rolate instance;

  private Rolate() {
    // Compresor Setup
    compressor = new Compressor();
    // compressor.stop();

    // Solonoid Setup
    // solenoid = new DoubleSolenoid(Constants.ROLATE_SOLONOID_A, Constants.ROLATE_SOLONOID_B);
    
    // Motors Setup
    // spinner = new TalonSRX(Constants.SPINNER_MOTOR);

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
   * Strat/Stop the compressor.
   * 
   * @param comp Start = true , Stop = false
   */
  public void set_compressor(final boolean comp) {
   if(comp) compressor.start();
   else compressor.stop();
  }

  /**
   * Set the solenoid to forword postion ("DoubleSolenoid.Value.kForward")
   */
  public void rolate_move_forword(){
    // solenoid.set(DoubleSolenoid.Value.kForward);
  }

  /**
   * Stop the solenoid ("DoubleSolenoid.Value.kOff")
   */
  public void rolate_stop(){
    // solenoid.set(DoubleSolenoid.Value.kOff);
  }

  /**
   * Set the solenoid to reverse postion ("DoubleSolenoid.Value.kReverse")
   */
  public void rolate_move_reverse(){
    // solenoid.set(DoubleSolenoid.Value.kReverse);
  }

  /**
   * Set the speed of the rolate motor
   * @param speed motors speed
   */
  public void spinner_set_speed(double speed){
    // spinner.set(ControlMode.PercentOutput, speed);
  }


}
