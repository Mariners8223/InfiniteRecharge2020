package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Bull;

public class CollectLemonCommand extends CommandBase {
  private Bull bull = Bull.getInstance();

  public CollectLemonCommand() {
    addRequirements(bull);
  }

  @Override
  public void execute() {
    bull.collect();
  }

  @Override
  public void end(boolean interrupted) {
    bull.collector_stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
