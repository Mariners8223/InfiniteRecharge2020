package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class SlowDrive extends CommandBase {
  private Chassis chassis = Chassis.getInstance();
  private double speed = -0.2;
  private double start_time;
  private double time;


  public SlowDrive(double time) {
    addRequirements(chassis);
    this.time = time;
    start_time = Timer.getFPGATimestamp();
  }
  public SlowDrive() {
    addRequirements(chassis);
    this.time = 5;
    start_time = Timer.getFPGATimestamp();
  }


  @Override
  public void initialize() {
    chassis.set_speed(speed, 0);//fix);
    
  }
  

  @Override
  public void execute() {
    // SmartDashboard.putNumber("encleft", chassis.enc_left.getDistance());
    // SmartDashboard.putNumber("encright", chassis.enc_right.getDistance());
    // double speed = chassis.get_deacceleration(chassis.get_distance());
    // double fix = chassis.gyro_calculate();
    // System.out.println(chassis.get_angle());
    // System.out.println(fix);
    chassis.set_speed(speed, 0);//fix);
    
  }

  @Override
  public void end(boolean interrupted) {
    chassis.set_speed(0, 0);
    System.out.println("fekhslfhskughskghrkghsghskghskgh");
  }

  @Override
  public boolean isFinished() {
    return (Timer.getFPGATimestamp() - start_time) > time;
  }
}