/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.Chassis;
import edu.wpi.first.wpilibj2.command.CommandBase;

import edu.wpi.first.wpilibj.Timer;

public class AngleVisionPidCommand extends CommandBase {
  
  private Chassis chassis = Chassis.getInstance();

  private double angle = 0;
  private double target_last_time = 0;
  private double wait = 0;

  public AngleVisionPidCommand() {
    addRequirements(chassis);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    chassis.pid_vision(angle) ;
  }

  @Override
  public void end(boolean interrupted) {
    chassis.pid_reset();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    boolean stop = chassis.stop_angle_vision_pid();
    if (!stop) {
      target_last_time = Timer.getFPGATimestamp();
    }
    return Timer.getFPGATimestamp() - target_last_time > wait && stop;
  }

}
