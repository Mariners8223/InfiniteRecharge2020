package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chassis;

public class ChassisCommand extends CommandBase {
  private final Chassis chassis = Chassis.getInstance();

  public ChassisCommand() {
    addRequirements(chassis);
  }

  @Override
  public void execute() {
    double x = RobotContainer.joystick.getRawAxis(1);
    double y = RobotContainer.joystick.getRawAxis(5);
    chassis.setSpeed(Math.signum(x) * x * x * 0.5, Math.signum(y) * y * y * 0.5);
  }

  @Override
  public void end(final boolean interrupted) {
    chassis.setSpeed(0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
