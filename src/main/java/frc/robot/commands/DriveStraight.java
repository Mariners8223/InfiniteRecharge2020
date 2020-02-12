package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;

public class DriveStraight extends CommandBase {
  private Chassis chassis = Chassis.getInstance();
  private double dist;

  public DriveStraight(double dist) {
    addRequirements(chassis);
    this.dist = dist;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    // chassis.pid_gyro_straight_execute();
  }

  @Override
  public void end(boolean interrupted) {
    chassis.reset_angle();
    chassis.set_speed(0, 0);
  }

  // Returns true when the command should end.
  // @Override
  // public boolean isFinished() {
  //   return chassis.get_distance() > dist;
  // }
}
