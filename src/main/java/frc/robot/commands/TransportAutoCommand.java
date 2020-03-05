package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Bull;

public class TransportAutoCommand extends CommandBase {
  double last_time;
  double time = 0;
  double need_t;

  Bull bull = Bull.getInstance();

  public TransportAutoCommand(double need_t) {
    this.need_t = need_t;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    last_time = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    bull.shot_set_speed(-1);
    System.out.println(bull.shoot_speed());
    if(bull.shoot_speed() < -30){
      time += Timer.getFPGATimestamp() - last_time;
      System.out.println("time: " + time);
      bull.trans_move_reverse();
      last_time = Timer.getFPGATimestamp();
    }
    else{
      bull.trans_stop();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    bull.shot_set_speed(0);
    bull.trans_stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return time > need_t;
  }
}
