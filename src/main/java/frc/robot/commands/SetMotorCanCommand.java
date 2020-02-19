package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SetMotorCanCommand extends CommandBase {
  private com.ctre.phoenix.motorcontrol.can.BaseMotorController motor;
  double speed;
  public SetMotorCanCommand(SubsystemBase subsystem, com.ctre.phoenix.motorcontrol.can.BaseMotorController motor, double speed) {
    addRequirements(subsystem);
    this.motor = motor;
    this.speed = speed;
  }

  public SetMotorCanCommand(com.ctre.phoenix.motorcontrol.can.BaseMotorController motor, double speed) {
    this.motor = motor;
    this.speed = speed;
  }

  @Override
  public void execute() {
    motor.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void end(boolean interrupted) {
    motor.set(ControlMode.PercentOutput, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}