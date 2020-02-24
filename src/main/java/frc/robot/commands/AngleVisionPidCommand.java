package frc.robot.commands;

import frc.robot.subsystems.Bull;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.NetworktablesSubSystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class AngleVisionPidCommand extends CommandBase {
  
  private Chassis chassis = Chassis.getInstance();
  private Bull bull = Bull.getInstance();
  private NetworktablesSubSystem NSS = NetworktablesSubSystem.getInstance();
  
  private double target_time = 0;

  private double target_last_time = 0;
  private double wait = 0;

  public AngleVisionPidCommand() {
    addRequirements(chassis);
    addRequirements(bull);
    addRequirements(NSS);
  }

  @Override
  public void execute() {
    chassis.pid_vision(SmartDashboard.getNumber("ang", 0));
    //System.out.println();
  }

  @Override
  public void end(boolean interrupted) {
    chassis.pid_reset();
  }

  @Override
  public boolean isFinished() {
    boolean stop = chassis.is_stop_angle_vision_pid();
    if (stop && target_last_time == 0) {
      target_time = Timer.getFPGATimestamp() - target_last_time;
    }
    else{
      target_last_time = 0;
    }
    return target_time > wait && stop;
  }

}
