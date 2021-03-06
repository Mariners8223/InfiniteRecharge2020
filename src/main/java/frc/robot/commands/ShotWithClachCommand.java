/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import java.lang.Math;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Bull;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShotWithClachCommand extends CommandBase {
  Bull bull = Bull.getInstance();

  private double start_time;
  TalonFX motor;

  public ShotWithClachCommand() {
    this.motor = bull.shoot;
  }

  
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("shoot_trigger", true);
    
    start_time = Timer.getFPGATimestamp();
    // motor.set(ControlMode.PercentOutput, -0.8);
  }
  /**
   * change the speed of the shoot from 0.6 to 1 by the left joystioc of the arms controller
   */
  @Override
  public void execute() {
    double speed = -RobotContainer.arms_joystick.getRawAxis(1);
    // speed = -(((speed+1)/2)*0.5+0.5);  // map ths speed from -1 1 to 0.6 1
    speed = -(((speed+1)/2)*0.3+0.7);  // map ths speed from -1 1 to 0.6 1
    speed = Math.floor(speed*20);
    speed = speed/20;
    motor.set(ControlMode.PercentOutput, -speed);
    SmartDashboard.putNumber("ShoterSpeed", -speed);
    SmartDashboard.putNumber("shoot speed", bull.enc_v_shooter());

    
  }

  /**
   * this command is 
   */
  @Override
  public void end(boolean interrupted) {
    bull.shoot_trigger = !bull.shoot_trigger;
    SmartDashboard.putBoolean("shoot_trigger", false);
    motor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public boolean isFinished() {
    if ((bull.enc_v_shooter() < 160) && (Timer.getFPGATimestamp() - start_time > 1)){
    //  return true; 
    }
    return false;
  }
}