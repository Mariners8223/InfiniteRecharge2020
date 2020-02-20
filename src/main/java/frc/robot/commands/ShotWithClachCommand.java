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

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShotWithClachCommand extends CommandBase {
  Bull bull = Bull.getInstance();

  VictorSPX motor;

  public ShotWithClachCommand() {
    this.motor = bull.shoot;
  }

  /**
   * change the speed of the shoot from 0.6 to 1 by the left joystioc of the arms controller
   */
  @Override
  public void execute() {
    double speed = -RobotContainer.arms_joystick.getRawAxis(Constants.DRIVER_LEFT_AXIS);
    speed = -(((speed+1)/2)*0.4+0.6);  // map ths speed from -1 1 to 0.6 1
    motor.set(ControlMode.PercentOutput, speed);
    SmartDashboard.putNumber("ShoterSpeed", speed);
  }

  /**
   * this command is 
   */
  @Override
  public void end(boolean interrupted) {
    bull.shoot_trigger = !bull.shoot_trigger;
    motor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}