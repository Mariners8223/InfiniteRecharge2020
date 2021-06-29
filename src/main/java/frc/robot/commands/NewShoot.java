/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Bull;

public class NewShoot extends CommandBase {
  Bull bull = Bull.getInstance();
  /**
   * Creates a new NewShoot.
   */
  public NewShoot() {
    addRequirements(bull);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    bull.shot_set_speed(0.6);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(bull.enc_v_shooter() < 1)
      bull.shot_set_speed(0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    bull.shot_set_speed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
