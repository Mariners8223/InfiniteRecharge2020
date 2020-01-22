package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Bull;

public class IntakeForwordCommand extends CommandBase {
  Bull bull = Bull.getInstance();

  public IntakeForwordCommand() {
    addRequirements(bull);
  }

  @Override
  public void execute() {
    bull.intake_move_forword();
  }

  @Override
  public void end(boolean interrupted) {
    bull.intake_stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
