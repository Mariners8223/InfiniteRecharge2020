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
    double speed = RobotContainer.arms_joystick.getRawAxis(Constants.DRIVER_LEFT_AXIS);
    motor.setSpeed(((speed+1)/2)*0.4+0.6);
  }

  @Override
  public void end(boolean interrupted) {
    motor.setSpeed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
