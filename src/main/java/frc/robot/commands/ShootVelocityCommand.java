/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Bull;
import edu.wpi.first.wpilibj.Timer;

public class ShootVelocityCommand extends CommandBase {
  Bull bull = Bull.getInstance();
  double speed;
  private double target_last_time = 0;
  private double wait = 0.3;

  public ShootVelocityCommand(double speed) {
    this.speed = -speed;
  }

  @Override
  public void initialize() {
    bull.shoot_enable(speed);
  }

  @Override
  public void execute() {
    bull.shoot();
  }

  @Override
  public void end(boolean interrupted) {
    bull.shoot_disable();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
