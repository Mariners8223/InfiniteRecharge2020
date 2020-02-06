package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class TurnToAngle extends CommandBase {
  private Chassis chassis = Chassis.getInstance();

  public TurnToAngle(double degrees) {
    addRequirements(chassis);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
  }

  @Override
  public void end(boolean interrupted) {
    chassis.set_speed(0, 0);
    chassis.reset_angle();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
