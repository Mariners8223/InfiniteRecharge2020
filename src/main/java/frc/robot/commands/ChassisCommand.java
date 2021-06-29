package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Bull;
import frc.robot.subsystems.NetworktablesSubSystem;

public class ChassisCommand extends CommandBase {
  private final Chassis chassis = Chassis.getInstance();
  private final NetworktablesSubSystem networktablesSubSystem = NetworktablesSubSystem.getInstance();
  private final Bull bull = Bull.getInstance();

  public ChassisCommand() {
    addRequirements(chassis);
    SmartDashboard.putNumber("Drive", -0.65);
    SmartDashboard.putNumber("Steering", 0.4);
  }

  @Override
  public void execute() { 
    
    //SmartDashboard.putNumber("encleft", chassis.enc_left.getDistance());
    //SmartDashboard.putNumber("encright", chassis.enc_right.getDistance());
    //SmartDashboard.putNumber("angle from serial", NetworktablesSubSystem.getInstance().get_angle());
    // System.out.println(NetworktablesSubSystem.getInstance().get_angle());
    double x = RobotContainer.driver_joystick.getRawAxis(Constants.DRIVER_LEFT_AXIS);
    double y = RobotContainer.driver_joystick.getRawAxis(Constants.DRIVER_RIGHT_AXIS);


    
    System.out.println("Testttttttttttttt");
    networktablesSubSystem.get_distance();

    
    double MaxSpeed = SmartDashboard.getNumber("Drive", 1);
    // double MaxSpeed = 0.2;
    double MaxSteering = SmartDashboard.getNumber("Steering", 1);
    SmartDashboard.putNumber("shoot speed", -bull.shoot_speed());
    chassis.set_speed(x * MaxSpeed, y * MaxSteering);
    // bull.enc_v_shooter();
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
