package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.NetworktablesSubSystem;

public class ChassisCommand extends CommandBase {
  private final Chassis chassis = Chassis.getInstance();
  private NetworktablesSubSystem nss = NetworktablesSubSystem.getInstance();
  public ChassisCommand() {
    addRequirements(chassis);
    SmartDashboard.putNumber("Drive", 0.5);
  }

  // @Override
  // public void execute() {
  //   chassis.Log();
  //   double x = RobotContainer.driver_joystick.getRawAxis(Constants.DRIVER_LEFT_AXIS);
  //   double y = RobotContainer.driver_joystick.getRawAxis(Constants.DRIVER_RIGHT_AXIS);
  //   double MaxSpeed = SmartDashboard.getNumber("Drive", 0.5);
  //   chassis.set_speed(Math.signum(x) * x * x * MaxSpeed, Math.signum(y) * y * y * MaxSpeed);
  //   SmartDashboard.putNumber("angle", nss.get_angle());
  // }

   
  /**
   * This is the joystick drive command
   */
  @Override
  public void execute() {
    chassis.Log();
    double y_joystick = RobotContainer.driver_joystick.getRawAxis(Constants.DRIVER_LEFT_AXIS);
    double x_joystick = RobotContainer.driver_joystick.getRawAxis(0);
 
    double MaxSpeed = SmartDashboard.getNumber("Max Speed", Constants.MAX_SPEED);
    double Turn_sensetivity = SmartDashboard.getNumber("Turn_sensetivity", Constants.Turn_sensetivity);
 
 
    double motor_left_speed = y_joystick - (x_joystick * Turn_sensetivity);
    double motor_right_speed = y_joystick + (x_joystick * Turn_sensetivity);
 
    chassis.set_speed(map_to_max_speed(motor_left_speed, MaxSpeed), map_to_max_speed(motor_right_speed, MaxSpeed));
    SmartDashboard.putNumber("angle", nss.get_angle());
  }
 
  /**
   * maps speed to max_speed range (0 to maxspeed)
   * @param speed speed
   * @param max_speed maximum speed
   * @return maped speed
   */
  private double map_to_max_speed(double speed, double max_speed){
      return max_speed * speed;
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
