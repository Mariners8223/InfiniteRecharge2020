package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Auto2_2Command extends SequentialCommandGroup {
  public Auto2_2Command() {
    //new SetMotorCanCommand(Bull.getInstance().shoot, -1);
    super(new DriveStraight(-2), new TurnToAngle(30), new TransportAutoCommand(5));
  }
}
