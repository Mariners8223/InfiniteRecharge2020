package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Everest extends SubsystemBase {
  private Spark piston;
  private Spark climber;

  public final double CLIMER_SPEED = 1.0;
  public final double PISTON_SPEED = 1.0;

  private static Everest instance;

  private Everest() {

    piston = new Spark(Constants.TRANS_MOTOR);
    climber = new Spark(Constants.SHOT_MOTOR);
  }

  public static Everest getInstance() {
    if (instance == null)
      instance = new Everest();
    return instance;
  }

  public void climer_set_speed(double speed){
    climber.set(speed);
  }
  
  public void electric_bochna_set_speed(double speed){
    piston.set(speed);
  }
}
