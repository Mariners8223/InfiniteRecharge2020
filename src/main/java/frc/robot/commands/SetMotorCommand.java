package frc.robot.commands;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SetMotorCommand extends CommandBase {
  private Spark motor;
  double speed;
  public SetMotorCommand(SubsystemBase subsystem, Spark motor, double speed) {
    addRequirements(subsystem);
    this.motor = motor;
    this.speed = speed;
  }

  @Override
  public void execute() {
    motor.set(speed);
  }

  @Override
  public void end(boolean interrupted) {
    motor.set(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}