package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Everest;

public class EverestPullupCommand extends CommandBase {
    Everest everest = Everest.getInstance();

  public EverestPullupCommand() {
    addRequirements(everest);
  }

  @Override
  public void execute() {
    everest.pullup_set_speed(-everest.PULLUP_SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    everest.pullup_set_speed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
