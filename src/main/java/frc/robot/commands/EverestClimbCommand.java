package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Everest;

public class EverestClimbCommand extends CommandBase {
    Everest everest = Everest.getInstance();

  public EverestClimbCommand() {
    addRequirements(everest);
  }

  @Override
  public void execute() {
    everest.climer_set_speed(everest.CLIMER_SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    everest.climer_set_speed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
