package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Everest extends SubsystemBase {
  public final Spark climber;
  public final Spark pullup1;
  public final Spark pullup2;

  public final double CLIMER_SPEED = 0.8;
  public final double PULLUP_SPEED = 0.7;

  private static Everest instance;

  private Everest() {
    pullup1 = new Spark(Constants.PULLUP1_MOTOR);
    pullup2 = new Spark(Constants.PULLUP2_MOTOR);
    climber = new Spark(Constants.CLIMER_MOTOR);
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
    climber.set(speed);
  }

  public void pullup_set_speed(double speed){
    pullup1.set(speed);
    pullup2.set(-speed);
  }
}
