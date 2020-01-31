package frc.robot.commands;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;

public class SetMotorCommand extends CommandBase {
  private Victor motor;
  double speed;
  public SetMotorCommand(Subsystem subsystem, int motor, double speed) {
    addRequirements(subsystem);
    this.motor = new Victor(motor);
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
