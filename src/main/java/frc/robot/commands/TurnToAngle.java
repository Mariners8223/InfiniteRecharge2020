package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpiutil.math.MathUtil;
import frc.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TurnToAngle extends CommandBase {
  private double degrees;
  private Chassis chassis = Chassis.getInstance();
  private double target_time = 0;
  private double target_last_time = 0;
  private double wait = 0.3;


  public TurnToAngle(double degrees) {
    addRequirements(chassis);
    this.degrees = degrees;
  }

  @Override
  public void initialize() {
    chassis.reset_angle();
    chassis.pid_turn_reset();
    chassis.pid_gyro_enable(degrees);
  }

  @Override
  public void execute() {
    double speed = chassis.gyro_turn_calculate();
    speed = MathUtil.clamp(speed, -chassis.PID_MAX_SPEED, chassis.PID_MAX_SPEED);
    chassis.set_speed(0, speed);
    SmartDashboard.putNumber("turn", speed);
  }

  @Override
  public void end(boolean interrupted) {
    chassis.set_speed(0, 0);
    
    chassis.reset_angle();
  }

  @Override
  public boolean isFinished() {
    boolean stop = chassis.stop_gyro();
    if (stop && target_last_time == 0) {
      target_time = Timer.getFPGATimestamp() - target_last_time;
    }
    else{
      target_last_time = 0;
    }
    return target_time > wait && stop;
  }
}
