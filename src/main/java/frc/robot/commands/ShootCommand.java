package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Bull;

public class ShootCommand extends CommandBase {
  Bull bull = Bull.getInstance();
  boolean dir;
  public ShootCommand(boolean dir) {
    this.dir = dir;
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    if(dir) bull.shot_set_speed(1);
    else bull.shot_set_speed(-1);
  }

  @Override
  public void end(boolean interrupted) {
    bull.shot_set_speed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
