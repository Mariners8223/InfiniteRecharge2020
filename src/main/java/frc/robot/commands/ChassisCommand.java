package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chassis;

public class ChassisCommand extends CommandBase {
  private final Chassis chassis = Chassis.getInstance();

  public ChassisCommand() {
    addRequirements(chassis);
    SmartDashboard.putNumber("Drive", 0.5);
  }

  @Override
  public void execute() {
    //SmartDashboard.putNumber("encleft", chassis.enc_left.getDistance());
    //SmartDashboard.putNumber("encright", chassis.enc_right.getDistance());
    double x = RobotContainer.driver_joystick.getRawAxis(Constants.DRIVER_LEFT_AXIS);
    double y = RobotContainer.driver_joystick.getRawAxis(Constants.DRIVER_RIGHT_AXIS);
    double MaxSpeed = SmartDashboard.getNumber("Drive", 1);
    chassis.set_speed(x * MaxSpeed, y * MaxSpeed);
  }

  @Override
  public void end(final boolean interrupted) {
    chassis.set_speed(0, 0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
