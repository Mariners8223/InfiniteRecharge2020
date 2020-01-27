package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Everest;

public class EverestDisPistonCommand extends CommandBase {
    Everest everest = Everest.getInstance();

  public EverestDisPistonCommand() {
    addRequirements(everest);
  }

  @Override
  public void execute() {
    everest.piston_set_speed(everest.PISTON_SPEED);
  }

  @Override
  public void end(boolean interrupted) {
    everest.piston_set_speed(0);
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
