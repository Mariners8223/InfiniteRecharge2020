package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Bull;

public class IntakeCommand extends CommandBase {
  Bull bull = Bull.getInstance();
  boolean dir;

  public IntakeCommand(boolean dir) {
    addRequirements(bull);
    this.dir = dir;
  }

  @Override
  public void execute() {
    if(dir) bull.intake_move_forword();
    else bull.intake_move_reverse();
  }

  @Override
  public void end(boolean interrupted) {
    //bull.intake_stop();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
