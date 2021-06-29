package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Bull;

public class transport extends CommandBase {
  double last_time;
  double time = 0;
  double need_t;

  Bull bull = Bull.getInstance();

  public transport(double need_t) {
    this.need_t = need_t;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    last_time = Timer.getFPGATimestamp();
    bull.shoot_enable(34);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    bull.shot_set_speed(0);
    bull.trans_stop();
    bull.shoot_disable();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
    // return time > need_t;
  }
}
