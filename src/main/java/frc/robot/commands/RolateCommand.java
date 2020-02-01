/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Rolate;

public class RolateCommand extends CommandBase {
  
  Rolate rolate = Rolate.getInstance();

  public RolateCommand() {
    addRequirements(rolate);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    rolate.spinner_set_speed(rolate.SPINNER_SPEED);
    rolate.rolate_move_reverse();
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    rolate.spinner_set_speed(0);
    rolate.rolate_stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
