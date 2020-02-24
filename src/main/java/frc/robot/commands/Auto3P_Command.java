/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
 
package frc.robot.commands;
 
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
 
// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
 
public class Auto3P_Command extends ParallelCommandGroup {
    //private static final int D = 7;
 
/**
   * Creates a new Auto3Command.
   */
  public Auto3P_Command() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new IntakeToggleCommand(), new DriveStraight(-Auto3_Command.dist));
  }
}
 

