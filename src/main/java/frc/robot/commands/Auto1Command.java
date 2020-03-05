package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Auto1Command extends SequentialCommandGroup {
  public Auto1Command() {
    //new SetMotorCanCommand(Bull.getInstance().shoot, -1);
    super(new DriveStraight(-1), new TransportAutoCommand(5));
  }
}
