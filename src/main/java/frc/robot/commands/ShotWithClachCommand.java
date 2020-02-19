/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Bull;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShotWithClachCommand extends CommandBase {
  Bull bull = Bull.getInstance();

  Spark motor;

  public ShotWithClachCommand() {
    this.motor = bull.shoot;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double speed = -RobotContainer.arms_joystick.getRawAxis(Constants.DRIVER_LEFT_AXIS);
    speed = ((speed+1)/2)*0.4+0.6;
    motor.setSpeed(speed);
    SmartDashboard.putNumber("ShoterSpeed", speed);
  }

  @Override
  public void end(boolean interrupted) {
    
    if (bull.shoot_trigger){
        motor.setSpeed(0);
        bull.shoot_trigger = false;
    } else {
        bull.shoot_trigger = true;
    }
  }

  @Override
  public boolean isFinished() {
    SmartDashboard.putBoolean("trig", bull.shoot_trigger);
    if (!bull.shoot_trigger){
        return true;
    }
    return false;
  }
}
