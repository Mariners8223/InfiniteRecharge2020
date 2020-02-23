package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Everest extends SubsystemBase {
  // Motors
  public final TalonSRX climber;
  public final VictorSPX pullup;

  // Constanse
  public final double CLIMER_SPEED = 0.5;
  public final double PULLUP_SPEED = 0.7;

  // Singletone Instance
  private static Everest instance;

  private Everest() {
    // Motors Setup
    pullup = new VictorSPX(Constants.PULLUP_MOTOR);
    climber = new TalonSRX(Constants.CLIMER_MOTOR);
  }
  
  /**
   * Singleton function, returns the ONLY instance of the class.
   * @return instance of the class.
   */
  public static Everest getInstance() {
    if (instance == null)
      instance = new Everest();
    return instance;
  }

  public void climer_set_speed(double speed){
    climber.set(ControlMode.PercentOutput, speed);
  }

  public void pullup_set_speed(double speed){
    pullup.set(ControlMode.PercentOutput, speed);
  }
}
