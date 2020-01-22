package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Bull;

public class IntakeReverseCommand extends CommandBase {
  Bull bull = Bull.getInstance();

  public IntakeReverseCommand() {
    addRequirements(bull);
  }

  @Override
  public void initialize() {
    bull.intake_move_reverse();
    System.out.println("safdkhlsfjahfsulfh");
  }

  @Override
  public void execute() {
    bull.intake_move_reverse();
    System.out.println("safdkhlsfjahfsulfh");
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
