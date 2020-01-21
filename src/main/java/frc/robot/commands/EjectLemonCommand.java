package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Bull;

public class EjectLemonCommand extends CommandBase {
  private Bull bull = Bull.getInstance();
  
  public EjectLemonCommand() {
    addRequirements(bull);
  }

  @Override
  public void execute() {
    bull.eject();
  }

  @Override
  public void end(final boolean interrupted) {
    bull.collector_stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
