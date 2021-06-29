package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Bull;

public class IntakeToggleCommand extends CommandBase {
  Bull bull = Bull.getInstance();

  public IntakeToggleCommand() {
    addRequirements(bull);
  }

  @Override
  public void initialize() {
    System.out.println("intake_toggle");
    bull.intake_move_reverse();
  }

  @Override
  public void execute() {
    if(RobotContainer.is_eject_button()){
        bull.collector_set_speed(-bull.COLLECTOR_SPEED);
    } else {
        bull.collector_set_speed(bull.COLLECTOR_SPEED);
    }
  }


  
  @Override
  public void end(boolean interrupted) {
    System.out.println("intake_toggle///////////////////////////");
    bull.collector_set_speed(0);
    bull.intake_move_forword();
    bull.intake_toggle = !bull.intake_toggle;
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
