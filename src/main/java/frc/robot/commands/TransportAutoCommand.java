package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    bull.shoot_enable(30);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    bull.shoot();
    SmartDashboard.putNumber("shoot speed", bull.shoot_speed());
    SmartDashboard.putNumber("shoot speed M", bull.shoot.getBusVoltage());
    SmartDashboard.putNumber("getSetpoint", bull.shooter_speed_pid.getSetpoint());
    SmartDashboard.putNumber("getPositionError", bull.shooter_speed_pid.getPositionError());
    SmartDashboard.putBoolean("bull.shoot_on_t()", bull.shoot_on_t());
    System.out.println(bull.shoot_on_t());
    if(bull.shoot_on_t()){
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
    bull.shoot_disable();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
    // return time > need_t;
  }
}
