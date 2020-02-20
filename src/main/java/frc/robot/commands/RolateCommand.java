package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Rolate;

public class RolateCommand extends CommandBase {
  
  Rolate rolate = Rolate.getInstance();

  public RolateCommand() {
    addRequirements(rolate);
  }

  @Override
  public void initialize() {
    rolate.spinner_set_speed(rolate.SPINNER_SPEED);
    rolate.rolate_move_reverse();
  }


  @Override
  public void end(boolean interrupted) {
    rolate.spinner_set_speed(0);
    rolate.rolate_move_forword();
    rolate.rolate_toggle = !rolate.rolate_toggle;
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
