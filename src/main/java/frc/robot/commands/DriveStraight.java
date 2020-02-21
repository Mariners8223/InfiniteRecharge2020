package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class DriveStraight extends CommandBase {
  private Chassis chassis = Chassis.getInstance();
  private double stop_val;
  private boolean is_time;
  private double start_time;

  public DriveStraight(double dist) {
    addRequirements(chassis);
    this.stop_val = dist;
    this.is_time = false;
  }

  public DriveStraight(double dist, boolean time) {
    addRequirements(chassis);
    this.stop_val = dist;
    this.is_time = time;
    if(is_time){
      start_time = Timer.getFPGATimestamp();
    }

  }

  @Override
  public void initialize() {
    chassis.deacceleration_enable(this.stop_val);
    chassis.reset_angle();
    chassis.pid_angle_reset();
    chassis.pid_acc_reset();
    chassis.enc_reset();
  }

  @Override
  public void execute() {
    double speed = chassis.get_deacceleration(chassis.get_distance());
    double fix = chassis.gyro_calculate();
    chassis.set_speed(speed-fix, speed+fix);
  }

  @Override
  public void end(boolean interrupted) {
    chassis.set_speed(0, 0);
  }

  @Override
  public boolean isFinished() {
    if (is_time){
      return Timer.getFPGATimestamp() - start_time > stop_val;
    }
    return chassis.get_distance() > stop_val;
  }
}