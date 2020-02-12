package frc.robot.commands;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Bull;

public class TansportCommand extends CommandBase {
  private Bull bull = Bull.getInstance();

  private double TIME_ON = 1;
  private double TIME_OFF = 1;
  private boolean is_on = true;
  private double time = 0;

  private Spark Motor;

  public TansportCommand(Spark motor) {
    addRequirements(bull);
    this.Motor = motor;
  }

  @Override
  public void initialize() {
    Motor.set(bull.TRANS_SPEED);
    time = Timer.getFPGATimestamp();
  }

  @Override
  public void execute() {
    double t = Timer.getFPGATimestamp() - time;
    if (is_on){
        if (t > TIME_ON){
            Motor.set(0);
            time += t;
        }
    } else {
        if (t > TIME_OFF){
            Motor.set(bull.TRANS_SPEED);
            time += t;
        }
    }
  }

  @Override
  public void end(boolean interrupted) {
      Motor.set(0);
  }

}
