package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Bull;

public class ShootCommand extends CommandBase {
  Bull bull = Bull.getInstance();
  public ShootCommand() {
    //addRequirements(bull);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    bull.shot_set_speed(1);
  }

  @Override
  public void end(boolean interrupted) {
    bull.shot_stop();
    
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
