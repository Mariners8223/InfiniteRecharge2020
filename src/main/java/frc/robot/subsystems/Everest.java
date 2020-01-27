package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Everest extends SubsystemBase {
  private Spark piston;
  private Spark climber;
  private Spark pullup;

  public final double CLIMER_SPEED = 1.0;
  public final double PISTON_SPEED = 1.0;
  public final double PULLUP_SPEED = 1.0;

  private static Everest instance;

  private Everest() {
    // pullup = new Spark(Constants.PULLUP_MOTOR);
    piston = new Spark(Constants.PISTON_MOTOR);
    climber = new Spark(Constants.CLIMER_MOTOR);
  }

  public static Everest getInstance() {
    if (instance == null)
      instance = new Everest();
    return instance;
  }

  public void climer_set_speed(double speed){
    climber.set(speed);
  }
  
  public void piston_set_speed(double speed){
    piston.set(speed);
  }

  public void pullup_set_speed(double speed){
    pullup.set(speed);
  }
}
