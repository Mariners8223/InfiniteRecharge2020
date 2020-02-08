package frc.robot.commands;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.Everest;

public class PullUpCommend extends CommandBase {

  Everest everset = Everest.getInstance();
  private boolean dir;

  public PullUpCommend(boolean dir) {
    addRequirements(everset);
    this.dir = dir;
  }

  @Override
  public void initialize() {
    if(dir) everset.pullup_set_speed(everset.PULLUP_SPEED);
    else everset.pullup_set_speed(-everset.PULLUP_SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    everset.pullup_set_speed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
