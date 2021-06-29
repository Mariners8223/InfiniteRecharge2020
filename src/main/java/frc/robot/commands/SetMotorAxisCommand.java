package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;

public class SetMotorAxisCommand extends CommandBase {
  private com.ctre.phoenix.motorcontrol.can.BaseMotorController motor;
  int axis;
  Joystick joystick;
  
  public SetMotorAxisCommand(com.ctre.phoenix.motorcontrol.can.BaseMotorController motor, int axis, Joystick stick) {
    this.motor = motor;
    this.axis = axis;
    this.joystick = stick;
  }

  @Override
  public void execute() {
    motor.set(ControlMode.PercentOutput, joystick.getRawAxis(axis));

  }

  @Override
  public boolean isFinished() {
    return false;
  }
}