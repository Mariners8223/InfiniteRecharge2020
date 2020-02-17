package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Chassis;
import frc.robot.subsystems.Bull;

public class AutoCommand extends CommandBase {
  Chassis chassis = Chassis.getInstance();
  Bull bull = Bull.getInstance();

  Command shoot = new ShootVelocityCommand(34);
  Command transport = new SetMotorCanCommand(bull.transportation, -0.5);


  public AutoCommand() {
    addRequirements(chassis);
    addRequirements(bull);
  }

  @Override
  public void initialize() {
    shoot.schedule();
    transport.schedule();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shoot.execute();
    SmartDashboard.putBoolean("stop", bull.shoot_stop());
    //transport.execute();
    if(bull.shoot_stop()){
      transport.execute();
    }else{
      //bull.transportation.set(ControlMode.PercentOutput, 0);
      //transport.schedule();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shoot.cancel();
    transport.cancel();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
