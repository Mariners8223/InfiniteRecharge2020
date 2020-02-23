package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    SmartDashboard.putNumber("encleft", chassis.enc_left.getDistance());
    SmartDashboard.putNumber("encright", chassis.enc_right.getDistance());
    double speed = chassis.get_deacceleration(chassis.get_distance());
    double fix = chassis.gyro_calculate();
    System.out.println(chassis.get_angle());
    System.out.println(fix);
    chassis.set_speed(speed, fix);
    
  }

  @Override
  public void end(boolean interrupted) {
    chassis.set_speed(0, 0);
    System.out.println("fekhslfhskughskghrkghsghskghskgh");
  }

  @Override
  public boolean isFinished() {
    return chassis.deacc_stop();
  }
}